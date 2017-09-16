package app.nisb.nisbapp

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import app.nisb.nisbapp.volley.APIController
import app.nisb.nisbapp.volley.VolleyService
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_event_single.*
import org.json.JSONException
import org.json.JSONObject
import java.text.SimpleDateFormat

class EventSingle : AppCompatActivity() {

    var eventdate : String = "YYYY-MM-DD"

    // display toast
    fun toaster(msg : String){
        Toast.makeText(applicationContext,msg, Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_single)


        val i = intent
        val id = i.getStringExtra("id")
        loadEvent(id)


        event_single_share.setOnClickListener({
            val text = event_single_desc.text
            shareEvent(text.toString())
        })

        event_single_remind.setOnClickListener({
            val titletext = event_single_title
            eventRemind(titletext.text.toString(),eventdate)
        })

    }

    private fun loadEvent(id: String) {
        val url = "https://graph.facebook.com/v2.8/"+ id + "?fields=" +
                "id%2C" + "cover%2C" + "name%2C" +"start_time%2C" + "place%2C" + "ticket_uri%2C" + "description" +
                "&access_token=1327383467301154%7CYDfQ94wTelbffydG5XrnanHnqu0"

        APIController(VolleyService()).get_string(url,{
            response ->
            try{
                val jsonObj: JSONObject = JSONObject(response);

                var title="";var cover="";var date="";var place="";var desc="";var attending="";var interested="";

                event_single_title.text = jsonObj.getString("name")

                if (jsonObj.has("cover")) {
                    cover = jsonObj.getJSONObject("cover").getString("source")
                    Picasso.with(getApplicationContext()).load(cover).into(event_single_cover)
                }

                if (jsonObj.has("start_time")){
                    date = jsonObj.getString("start_time")
                    eventdate = date.substring(0,10)
                }

                if (jsonObj.has("place"))
                    place = jsonObj.getJSONObject("place").getString("name")

                event_single_extra.text = "Date : " + date.substring(0,10) +" , Place : " + place


                if (jsonObj.has("description"))
                    event_single_desc.text = jsonObj.getString("description")

                if (jsonObj.has("ticket_uri")){
                    val ticket_uri = jsonObj.getString("ticket_uri")
                    event_single_register.setVisibility(View.VISIBLE)
                    event_single_register.setOnClickListener( {
                        val i: Intent = Intent(Intent.ACTION_VIEW)
                        i.setData(Uri.parse(ticket_uri))
                        startActivity(i);
                    })
                }

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

    fun eventRemind(summary: String, date : String){

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
