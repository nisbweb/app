package `in`.nisb.nisbapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView

import org.json.JSONException
import org.json.JSONObject

/**
 * Created by mridul on 28/3/17.
 */

class NotificationFragment : Fragment() {
    internal var view: View? = null
    internal var sr: SwipeRefreshLayout? = null
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        view = inflater!!.inflate(R.layout.activity_notifs, container, false)

        loadList()
        val tv_clear = view?.findViewById(R.id.notifs_clear) as TextView
        tv_clear.setOnClickListener {
            DatabaseFunctions().clearNotifications(context)
            loadList()
        }


        sr = view?.findViewById(R.id.notifs_refresh) as SwipeRefreshLayout
        sr?.setOnRefreshListener { loadList() }

        return view
    }

    internal fun loadList() {
        val lv = view?.findViewById(R.id.notifs_list) as ListView
        val notifs_list = DatabaseFunctions().getNotifications(context)
        if (notifs_list != null)
            lv.adapter = NotificationAdapter(context, notifs_list)
        else {
            val empty = arrayOf<String>()
            lv.adapter = ArrayAdapter(context, R.layout.notifs_list_item, empty)
        }


        lv.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val tv_data = view?.findViewById(R.id.notifs_data) as TextView
            try {
                val jo = JSONObject(tv_data.text.toString())
                val context = jo.getString("context")
                var intent: Intent
                if (context == "blog-single") {
                    intent = Intent(getContext(), BlogSingleActivity::class.java)
                    intent.putExtra("url", jo.getString("url"))
                    startActivity(intent)
                }
                if (context == "event-single") {
                    intent = Intent(getContext(), EventSingleActivity::class.java)
                    intent.putExtra("id", jo.getString("id"))
                    startActivity(intent)
                }
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }
        (view?.findViewById(R.id.notifs_refresh) as SwipeRefreshLayout).isRefreshing = false

    }

    private inner class NotificationAdapter(internal var c: Context, internal var jo: Array<JSONObject?>?) : ArrayAdapter<JSONObject>(c, R.layout.notifs_list_item, jo) {
        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {

            var cV = convertView ?: LayoutInflater.from(c).inflate(R.layout.notifs_list_item, parent, false)

            val tv_title = cV.findViewById(R.id.notifs_text) as TextView
            val tv_data = cV.findViewById(R.id.notifs_data) as TextView
            try {
                val title = jo!![position]!!.getString("body")
                tv_title.text = title
                tv_data.text = jo!![position].toString()
            } catch (j: JSONException) {
            }

            return cV
        }
    }
}
