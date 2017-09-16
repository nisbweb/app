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
import app.nisb.nisbapp.R
import app.nisb.nisbapp.volley.APIController
import app.nisb.nisbapp.volley.VolleyService
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_blog.*
import kotlinx.android.synthetic.main.item_blog.view.*
import org.json.JSONException
import org.json.JSONObject

/**
 * Created by mridul on 6/24/17.
 */
class BlogFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_blog, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        blog_list.layoutManager = LinearLayoutManager(context)
        blog_list.hasFixedSize()
        loadBlogs()
    }

    // display toast
    fun toaster(msg : String){
        Toast.makeText(context,msg, Toast.LENGTH_SHORT).show()
    }


    public fun newInstance(text: String): BlogFragment {

        val f = BlogFragment()
        val b = Bundle()
        b.putString("msg", text)

        f.setArguments(b)

        return f
    }

    fun loadBlogs(){
        val url = "https://api.tumblr.com/v2/blog/nisbieee.tumblr.com/posts/text?api_key=piB4a6q09ID1pysr9aX6HXUI1CWf3nhSSx1hQHSwL98uED9wNk&notes_info=true"

        APIController(VolleyService()).get_string(url,{
            response ->
            try {

                val jsonObj = JSONObject(response)
                val ja = jsonObj.getJSONObject("response").getJSONArray("posts")
                val b = arrayOfNulls<JSONObject>(ja.length())
                for (i in 0..ja.length() - 1) {
                    b[i] = ja.getJSONObject(i)
                }
                blog_list.adapter = BlogAdapter(context, b)

            } catch (j: JSONException) {
                toaster("Error ")
            }
            catch (e: NullPointerException){
                toaster("Couldn't find posts.")
            }
        })
    }


    class BlogAdapter(var c: Context, var lists: Array<JSONObject?>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder? {
            var v = LayoutInflater.from(c).inflate(R.layout.item_blog, parent, false)

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

                itemView.blog_adapter_name.text = _list!!.getString("title")
                itemView.blog_adapter_extra.text = _list!!.getString("date").substring(0,10)
                itemView.blog_adapter_id.text = _list!!.getString("id")

                itemView.setOnClickListener({
                    val id = _list!!.getString("id")
                    val intent = Intent(c, BlogSingle::class.java)
                    intent.putExtra("id", id)
                    c.startActivity(intent)
                })
            }
        }
    }

}// Required empty public constructor
