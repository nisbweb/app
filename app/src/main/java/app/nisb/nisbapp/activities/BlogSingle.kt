package app.nisb.nisbapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebSettings
import android.widget.Toast
import app.nisb.nisbapp.fragments.BlogFragment
import app.nisb.nisbapp.volley.APIController
import app.nisb.nisbapp.volley.VolleyService
import kotlinx.android.synthetic.main.activity_blog_single.*
import kotlinx.android.synthetic.main.fragment_blog.*
import org.json.JSONException
import org.json.JSONObject

class BlogSingle : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blog_single)

        val i = intent
        val id = i.getStringExtra("id")
        loadBlog(id)
    }

    // display toast
    fun toaster(msg : String){
        Toast.makeText(applicationContext,msg, Toast.LENGTH_SHORT).show()
    }


    private fun loadBlog(id: String?) { //164505331698
        val url = "https://api.tumblr.com/v2/blog/nisbieee.tumblr.com/posts/text?id=" + id + "&api_key=piB4a6q09ID1pysr9aX6HXUI1CWf3nhSSx1hQHSwL98uED9wNk&notes_info=true"

        APIController(VolleyService()).get_string(url,{
            response ->
            try {

                val j = JSONObject(response).getJSONObject("response").getJSONArray("posts").getJSONObject(0)

                val title = j.getString("title")
                var html = j.getString("body")
                html = html.replace("<img", "<img width=\"100%\"")

                val notes = j.getString("note_count")
                val url = j.getString("short_url")

                blog_single_title.text = title
                blog_single_wv.settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN
//                blog_single_wv.loadUrl(url)
                blog_single_wv.loadDataWithBaseURL(null, "<br>$html<br><br><br>", "text/html", "utf-8", null)

            } catch (j: JSONException) {
                toaster("Error ")
            }
        })
    }
}
