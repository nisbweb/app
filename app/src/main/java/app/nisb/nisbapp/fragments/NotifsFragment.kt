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
import app.nisb.nisbapp.BlogSingle
import app.nisb.nisbapp.DBFunc
import app.nisb.nisbapp.EventSingle
import app.nisb.nisbapp.R
import kotlinx.android.synthetic.main.fragment_blog.*
import kotlinx.android.synthetic.main.fragment_notifs.*
import kotlinx.android.synthetic.main.item_notif.view.*
import org.json.JSONObject

/**
 * Created by mridul on 6/24/17.
 */
class NotifsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_notifs, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        notif_clear.setOnClickListener({
            DBFunc().clearNotifications(context)
            loadNotifs()
        })

        notif_list.layoutManager = LinearLayoutManager(context)
        notif_list.hasFixedSize()
        loadNotifs()
    }

    public fun newInstance(text: String): NotifsFragment {

        val f = NotifsFragment()
        val b = Bundle()
        b.putString("msg", text)

        f.setArguments(b)

        return f
    }


    // display toast
    fun toaster(msg : String){
        Toast.makeText(context,msg, Toast.LENGTH_SHORT).show()
    }


    fun loadNotifs(){

        try {
            val arr = DBFunc().getNotifications(context)!!

            val b = arrayOfNulls<JSONObject>(arr.size)
            for (i in 0..arr.size - 1) {
                b[i] = arr[i]
            }
            notif_list.adapter = NotifsAdapter(context, b)
        }
        catch (e : NullPointerException){
            notif_list.adapter = null
        }
    }



    class NotifsAdapter(var c: Context, var lists: Array<JSONObject?>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder? {
            var v = LayoutInflater.from(c).inflate(R.layout.item_notif, parent, false)
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
                itemView.notif_adapter_body.text = _list!!.getString("body")
                if (_list!!.has("context")){
                    itemView.notif_adapter_context.text = _list!!.getString("context")
                    itemView.notif_adapter_id.text = _list!!.getString("id")

                    itemView.setOnClickListener({
                        if (itemView.notif_adapter_context.text.equals("event")){
                            val intent = Intent(c, EventSingle::class.java)
                            intent.putExtra("id", _list!!.getString("id"))
                            c.startActivity(intent)
                        }
                        else if (itemView.notif_adapter_context.text.equals("blog")){
                            val intent = Intent(c, BlogSingle::class.java)
                            intent.putExtra("id", _list!!.getString("id"))
                            c.startActivity(intent)
                        }
                    })
                }

            }
        }
    }

}// Required empty public constructor



