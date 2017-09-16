package app.nisb.nisbapp.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.nisb.nisbapp.BlogSingle
import app.nisb.nisbapp.DBFunc
import app.nisb.nisbapp.EventSingle
import app.nisb.nisbapp.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_event.*
import kotlinx.android.synthetic.main.fragment_notifs.*
import kotlinx.android.synthetic.main.item_event.view.*
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

        loadNotifs()
    }

    public fun newInstance(text: String): NotifsFragment {

        val f = NotifsFragment()
        val b = Bundle()
        b.putString("msg", text)

        f.setArguments(b)

        return f
    }


    fun loadNotifs(){
        val jo : Array<JSONObject?>? = DBFunc().getNotifications(context)
        System.out.print(jo.toString())
        if (jo==null ){
            System.out.println("YOU GOT NOTHIN")
            notif_list.adapter=null
        }
        else{
            notif_list.adapter = NotifAdapter(context, jo!!)
        }

    }



    class NotifAdapter(var c: Context, var lists: Array<JSONObject?>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

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
            fun bindData(_list: JSONObject?, c : Context) {
                itemView.notif_adapter_body.text = _list!!.getString("body")
                itemView.notif_adapter_context.text = _list!!.getString("context")
                itemView.notif_adapter_id.text = _list!!.getString("id")

                itemView.setOnClickListener({
                    val id = _list!!.getString("id")
                    if (_list!!.getString("context").toString().equals("event")){
                        val intent = Intent(c, EventSingle::class.java)
                        intent.putExtra("id", id)
                        c.startActivity(intent)
                    }
                    else if (_list!!.getString("context").toString().equals("blog")){
                        val intent = Intent(c, BlogSingle::class.java)
                        intent.putExtra("id", id)
                        c.startActivity(intent)
                    }

                })
            }
        }
    }// Required empty public constructor


}// Required empty public constructor



