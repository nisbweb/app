package `in`.nisb.nisbapp

import `in`.nisb.nisbapp.volley.APIController
import `in`.nisb.nisbapp.volley.VolleyService
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.ImageView
import android.widget.TabHost
import android.widget.TextView

import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.squareup.picasso.Picasso

import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

/**
 * Created by mridul on 19/3/17.
 */

class EventFragment : Fragment() {

    internal var view: View? = null

    internal var gview_all: GridView? = null
    internal var gview_cs: GridView? = null
    internal var sr: SwipeRefreshLayout? = null
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        view = inflater!!.inflate(R.layout.activity_events, container, false)


        val t = view!!.findViewById(R.id.events_tabhost) as TabHost
        t.setup()

        sr = view?.findViewById(R.id.events_refresh) as SwipeRefreshLayout
        sr?.setOnRefreshListener { loadGrids() }
        sr = view?.findViewById(R.id.events_refresh2) as SwipeRefreshLayout
        sr?.setOnRefreshListener { loadGrids() }

        val spec1 = t.newTabSpec("all")
        spec1.setIndicator("All NISB Events")
        spec1.setContent(R.id.events_all)
        t.addTab(spec1)

        val spec2 = t.newTabSpec("cs")
        spec2.setIndicator("Computer Society")
        spec2.setContent(R.id.events_cs)
        t.addTab(spec2)

        t.tabWidget.getChildAt(0).setBackgroundColor(resources.getColor(R.color.colorPrimaryDark))

        (t.getTabWidget().getChildAt(0).findViewById(android.R.id.title) as TextView).setTextColor(resources.getColor(R.color.colorAccent))
        (t.getTabWidget().getChildAt(1).findViewById(android.R.id.title) as TextView).setTextColor(resources.getColor(R.color.colorAccent))

        t.setOnTabChangedListener { tabId ->
            if (tabId == "cs") {
                t.tabWidget.getChildAt(0).setBackgroundColor(resources.getColor(R.color.colorPrimaryDark))
                t.tabWidget.getChildAt(1).setBackgroundColor(resources.getColor(R.color.colorPrimary))

            } else {
                t.tabWidget.getChildAt(1).setBackgroundColor(resources.getColor(R.color.colorPrimaryDark))
                t.tabWidget.getChildAt(0).setBackgroundColor(resources.getColor(R.color.colorPrimary))
            }
            //Toast.makeText(getContext(),tabId,Toast.LENGTH_SHORT).show();
        }

        loadGrids()

        return view
    }

    private fun loadGrids() {
        gview_all = view?.findViewById(R.id.events_all_grid) as GridView
        gview_cs = view?.findViewById(R.id.events_cs_grid) as GridView

        getEvents()
        getCSEvents()

        gview_all?.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val i = Intent(context, EventSingleActivity::class.java)
            i.putExtra("id", (view.findViewById(R.id.events_adapter_id) as TextView).text)
            startActivity(i)
        }
        gview_cs?.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val i = Intent(context, EventSingleActivity::class.java)
            i.putExtra("id", (view.findViewById(R.id.events_adapter_id) as TextView).text)
            startActivity(i)
        }

    }


    internal fun getEvents() {
        val fields = arrayOf("id", "name", "cover", "start_time")
        val url = ExtraFunctions.fbUrlGenerator("nieieeestudentbranch/events", fields)
        APIController(VolleyService()).get_string(url,{
            response ->
            try {
                val jsonObj = JSONObject(response)
                val ja = jsonObj.getJSONArray("data")
                val b = arrayOfNulls<JSONObject>(ja.length())
                for (i in 0..ja.length() - 1) {
                    b[i] = ja.getJSONObject(i)
                }
                gview_all?.adapter = EventsAdapter(b)
                (view?.findViewById(R.id.events_refresh) as SwipeRefreshLayout).isRefreshing = false
            } catch (j: JSONException) {
            }
        })
    }

    internal fun getCSEvents() {
        val fields = arrayOf("id", "name", "cover", "start_time")
        val url = ExtraFunctions.fbUrlGenerator("nieieeecomputersociety/events", fields)
        APIController(VolleyService()).get_string(url, {
            response ->
            try {
                val jsonObj = JSONObject(response)
                val ja = jsonObj.getJSONArray("data")
                val b = arrayOfNulls<JSONObject>(ja.length())
                for (i in 0..ja.length() - 1) {
                    b[i] = ja.getJSONObject(i)
                }
                gview_cs!!.adapter = EventsAdapter(b)
                (view?.findViewById(R.id.events_refresh2) as SwipeRefreshLayout).isRefreshing = false
            } catch (j: JSONException) {
            }
        })
    }

    private inner class EventsAdapter// Constructor
    ( private val b: Array<JSONObject?>) : BaseAdapter() {

        override fun getCount(): Int {
            //while (b==null){ }
            return b.size
        }

        override fun getItem(position: Int): Any? {
            return b[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }


        // create a new ImageView for each item referenced by the Adapter
        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {

            val cV: View = convertView ?: LayoutInflater.from(context).inflate(R.layout.events_grid_item, parent, false)

            val imageView = cV.findViewById(R.id.events_adapter_image) as ImageView
            val t1 = cV.findViewById(R.id.events_adapter_title) as TextView
            val t2 = cV.findViewById(R.id.events_adapter_id) as TextView

            try {
                t1.text = b[position]?.getString("name")
                t2.text = b[position]?.getString("id")
                val cover = b[position]!!.getJSONObject("cover").getString("source")
                imageView.adjustViewBounds = true
                Picasso.with(context).load(cover).into(imageView)

            } catch (e: JSONException) {
                e.printStackTrace()
            }

            return cV
        }


    }
}
