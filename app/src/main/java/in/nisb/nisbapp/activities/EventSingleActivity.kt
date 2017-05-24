package `in`.nisb.nisbapp

import `in`.nisb.nisbapp.R.drawable.event
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

import com.squareup.picasso.Picasso

import org.json.JSONException
import org.json.JSONObject

import `in`.nisb.nisbapp.volley.APIController
import `in`.nisb.nisbapp.volley.VolleyService
import android.icu.util.Calendar
import android.os.Build
import android.support.annotation.RequiresApi
import android.widget.Toast
import java.text.SimpleDateFormat


class EventSingleActivity : AppCompatActivity() {

    var eventdate : String = "YYYY-MM-DD"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_single)

        ExtraFunctions.setSBColor(window, Color.parseColor("#95a5a6"))

        val i = intent
        val id = i.getStringExtra("id")
        loadEvent(id)


        val btn_share = findViewById(R.id.events_single_share) as Button
        btn_share.setOnClickListener({
            val text = findViewById(R.id.events_single_text) as TextView
            shareEvent(text.text.toString())
        })
        val btn_remind = findViewById(R.id.events_single_remind) as Button
        btn_remind.setOnClickListener({
            val titletext = findViewById(R.id.events_single_title) as TextView
            insertEvent(titletext.text.toString(),eventdate)
        })
    }

    fun loadEvent(eventid: String) {
        val fields = arrayOf("name", "photos", "place", "start_time", "description", "cover", "ticket_uri")
        val url = ExtraFunctions.fbUrlGenerator(eventid, fields)
        APIController(VolleyService()).get_string(url,{
            response ->
            try{
                val jsonObj: JSONObject = JSONObject(response);

                var title="";var cover="";var date="";var place="";var desc="";var attending="";var interested="";

                val tv_title =  findViewById(R.id.events_single_title) as TextView
                val tv_extra =  findViewById(R.id.events_single_extra) as TextView
                val tv_text =  findViewById(R.id.events_single_text) as TextView
                val btnreg =  findViewById(R.id.events_register) as Button

                title = jsonObj.getString("name");
                if (jsonObj.has("cover"))
                    cover = jsonObj.getJSONObject("cover").getString("source");
                date = jsonObj.getString("start_time");
                if (jsonObj.has("description"))
                    desc = jsonObj.getString("description");
                if (jsonObj.has("place"))
                    place = jsonObj.getJSONObject("place").getString("name");
                if (jsonObj.has("ticket_uri")){
                    val ticket_uri = jsonObj.getString("ticket_uri");
                    btnreg.setVisibility(View.VISIBLE);
                    btnreg.setOnClickListener( {
                        val i: Intent = Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(ticket_uri));
                        startActivity(i);
                    })
                }

                val imageView = findViewById(R.id.events_single_cover) as ImageView
                Picasso.with(getApplicationContext()).load(cover).into(imageView);

                tv_title.setText(title);
                tv_extra.setText("Date : " + date.substring(0,10) +" , Place : " + place);
                eventdate = date.substring(0,10)
                tv_text.setText(desc);

            }
            catch (j: JSONException){ }
        })

    }

    fun shareEvent(text: String){
        val sendIntent = Intent()
        sendIntent.action = Intent.ACTION_SEND
        sendIntent.putExtra(Intent.EXTRA_TEXT, text)
        sendIntent.type = "text/plain"
        startActivity(Intent.createChooser(sendIntent, "Share NISB Event"))
    }

    fun insertEvent(summary: String, date : String){

        val sdf = SimpleDateFormat("yyyy-MM-dd")
        val date2 = sdf.parse(date)
        val dateinmillis = (date2.getTime())

        val intent = Intent(Intent.ACTION_EDIT)
        intent.type = "vnd.android.cursor.item/event"
        intent.putExtra("beginTime", dateinmillis)
        intent.putExtra("allDay", true)
        intent.putExtra("endTime", dateinmillis + 60 * 60 * 1000)
        intent.putExtra("title", summary)
        startActivity(intent)

    }

}
