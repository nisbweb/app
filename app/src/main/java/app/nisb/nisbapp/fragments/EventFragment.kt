package app.nisb.nisbapp.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import app.nisb.nisbapp.EventSingle
import app.nisb.nisbapp.MainActivity
import app.nisb.nisbapp.R
import app.nisb.nisbapp.volley.APIController
import app.nisb.nisbapp.volley.VolleyService
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_event.*;
import kotlinx.android.synthetic.main.item_event.view.*
import org.json.*

/**
 * Created by mridul on 6/24/17.
 */
class EventFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_event, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        event_list.layoutManager = LinearLayoutManager(context)
        event_list.hasFixedSize()
        loadAll()
        //toaster("Start get")

        event_all.setOnClickListener({
            loadAll()
        })
        event_cs.setOnClickListener({
            loadCS()
        })
        event_wie.setOnClickListener({
            loadWIE()
        })


    }


    // display toast
    fun toaster(msg : String){
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show()
    }

    public fun newInstance(text: String): EventFragment {

        val f = EventFragment()
        val b = Bundle()
        b.putString("msg", text)

        f.setArguments(b)

        return f
    }


    fun loadAll(){
        val url = "https://graph.facebook.com/v2.8/nieieeestudentbranch/events?fields=" +
                    "id%2C" + "cover%2C" + "name" +
                    "&access_token=1327383467301154%7CYDfQ94wTelbffydG5XrnanHnqu0"

        APIController(VolleyService()).get_string(url,{
            response ->
            try {

                val jsonObj = JSONObject(response)
                val ja = jsonObj.getJSONArray("data")
                val b = arrayOfNulls<JSONObject>(ja.length())
                for (i in 0..ja.length() - 1) {
                    b[i] = ja.getJSONObject(i)
                }
                if (ja.length()==0)
                    event_list.adapter=null
                else
                    event_list.adapter = EventAdapter(context,b)

            } catch (j: JSONException) {
                toaster("Error ")
            }
            catch (n: NullPointerException){

            }
        })
    }

    fun loadCS(){
        val url = "https://graph.facebook.com/v2.8/nieieeecomputersociety/events?fields=" +
                "id%2C" + "cover%2C" + "name" +
                "&access_token=1327383467301154%7CYDfQ94wTelbffydG5XrnanHnqu0"

        APIController(VolleyService()).get_string(url,{
            response ->
            try {

                val jsonObj = JSONObject(response)
                val ja = jsonObj.getJSONArray("data")
                val b = arrayOfNulls<JSONObject>(ja.length())
                for (i in 0..ja.length() - 1) {
                    b[i] = ja.getJSONObject(i)
                }
                event_list.adapter = EventAdapter(context,b)

            } catch (j: JSONException) {
                toaster("Error ")
            }
        })
    }

    fun loadWIE(){
        val url = "https://graph.facebook.com/v2.8/nieieeewie/events?fields=" +
                "id%2C" + "cover%2C" + "name" +
                "&access_token=1327383467301154%7CYDfQ94wTelbffydG5XrnanHnqu0"

        APIController(VolleyService()).get_string(url,{
            response ->
            try {

                val jsonObj = JSONObject(response)
                val ja = jsonObj.getJSONArray("data")
                val b = arrayOfNulls<JSONObject>(ja.length())
                for (i in 0..ja.length() - 1) {
                    b[i] = ja.getJSONObject(i)
                }
                event_list.adapter = EventAdapter(context,b)

            } catch (j: JSONException) {
                toaster("Error ")
            }
        })
    }


    class EventAdapter(var c: Context, var lists: Array<JSONObject?>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder? {
            var v = LayoutInflater.from(c).inflate(R.layout.item_event, parent, false)
            return Item(v)
        }

        override fun getItemCount(): Int {
            return lists.size
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
            (holder as Item).bindData(lists[position],c)
        }

        class Item(itemView: View) : RecyclerView.ViewHolder(itemView) {
            fun bindData(_list: JSONObject?,c : Context) {
                itemView.event_adapter_name.text = _list!!.getString("name")
                val cover = _list.getJSONObject("cover").getString("source")
                itemView.event_adapter_cover.adjustViewBounds=true
                Picasso.with(c).load(cover).into(itemView.event_adapter_cover)

                itemView.setOnClickListener({
                    val id = _list!!.getString("id")
                    val intent = Intent(c, EventSingle::class.java)
                    intent.putExtra("id", id)
                    c.startActivity(intent)
                })
            }
        }
    }

}// Required empty public constructor
