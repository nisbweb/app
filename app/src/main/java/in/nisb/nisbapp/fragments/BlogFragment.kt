package `in`.nisb.nisbapp

import `in`.nisb.nisbapp.volley.APIController
import `in`.nisb.nisbapp.volley.VolleyService
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import org.json.JSONArray

import org.json.JSONException
import org.json.JSONObject
import org.w3c.dom.Text

/**
 * Created by mridul on 19/3/17.
 */

class BlogFragment : Fragment() {

    var vieww: View? =null
    var sr: SwipeRefreshLayout? = null
    var gview: GridView? = null

    internal var blogs: Array<JSONObject>? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        vieww = inflater!!.inflate(R.layout.activity_blog, container, false)

        gview = vieww?.findViewById(R.id.blog_grid) as GridView
        getBlogs()

        sr = vieww?.findViewById(R.id.blog_refresh) as SwipeRefreshLayout
        sr?.setOnRefreshListener { getBlogs() }

        gview?.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val i = Intent(context, BlogSingleActivity::class.java)
            i.putExtra("title", (view.findViewById(R.id.blog_adapter_title) as TextView).text.toString())
            i.putExtra("extra", (view.findViewById(R.id.blog_adapter_extra) as TextView).text.toString())
            i.putExtra("content", (view.findViewById(R.id.blog_adapter_content) as TextView).text.toString())
            i.putExtra("url",(view.findViewById(R.id.blog_adapter_url) as TextView).text.toString())
            startActivity(i)
        }

        return vieww
    }

    fun toastit(text: String){
        Toast.makeText(context,text,Toast.LENGTH_SHORT).show()
    }

    fun getBlogs() {

        APIController(VolleyService())
            .get_string("http://blog.nisb.in/api/get_recent_posts/",{
            response ->

                try{
                    val jsonObj = JSONObject(response)
                    val ja = jsonObj.getJSONArray("posts")
                    val b = arrayOfNulls<JSONObject>(ja.length())

                    var i=0
                    for (i in 0..ja.length()-1)
                        b[i] = ja.getJSONObject(i)

                    gview!!.adapter = (BlogAdapter(b))

                    (vieww?.findViewById(R.id.blog_refresh) as SwipeRefreshLayout).isRefreshing=false
                }
                catch (j: JSONException){
                    toastit("ERROR ${j.toString()}")
                    j.printStackTrace()
                }
        })
    }


    private inner class BlogAdapter
    (private val b: Array<JSONObject?>) : BaseAdapter() {

        override fun getCount(): Int {
            return b.size
        }

        override fun getItem(position: Int): Any? {
            return b[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {

            val convertView = convertView ?: LayoutInflater.from(context).inflate(R.layout.blog_grid_item, parent, false)

            val tv_title = convertView.findViewById(R.id.blog_adapter_title) as TextView
            val tv_extra = convertView.findViewById(R.id.blog_adapter_extra) as TextView
            val tv_text = convertView.findViewById(R.id.blog_adapter_text) as TextView
            val tv_content = convertView.findViewById(R.id.blog_adapter_content) as TextView
            val tv_url = convertView.findViewById(R.id.blog_adapter_url) as TextView

            try {
                tv_title.text = Html.fromHtml(b[position]?.getString("title_plain"))
                tv_extra.text = "${b[position]?.getJSONObject("author")!!.getString("name")}   ${b[position]?.getString("date")}"
                tv_text.text = Html.fromHtml(b[position]?.getString("excerpt"))
                tv_content.text = b[position]!!.getString("content").replace("<img", "<img width=\"100%\"")
                tv_url.text = b[position]!!.getString("url")

                var comments: String = ""
                val comjsonA: JSONArray = b[position]!!.getJSONArray("comments")

                if (comjsonA.length()>0){
                    var i=0
                    for (i in 0..comjsonA.length()-1){
                        comments += "<b> ${comjsonA.getJSONObject(i).getString("name")} </b><br>"
                        comments += comjsonA.getJSONObject(i).getString("content")
                        comments +="<br><br>"
                    }
                    comments = "<br><br><br><br><center><h2>Comments</h2></center> $comments "
                    tv_content.text = tv_content.text.toString() + comments
                }

            } catch (e: JSONException) {
                Log.d("ERROR", e.toString())
                toastit(e.toString())
                e.printStackTrace()
            }

            return convertView
        }
    }

}