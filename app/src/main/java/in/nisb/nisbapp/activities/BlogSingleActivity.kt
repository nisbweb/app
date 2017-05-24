package `in`.nisb.nisbapp

import `in`.nisb.nisbapp.volley.APIController
import `in`.nisb.nisbapp.volley.VolleyService
import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.webkit.WebSettings
import android.webkit.WebView
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import org.w3c.dom.Text
import java.net.URLEncoder

class BlogSingleActivity : AppCompatActivity() {

    var blogtitle="";var url="";var blogid="";var author="";var date=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blog_single)

        ExtraFunctions.setSBColor(window, Color.parseColor("#95a5a6"))

        val i = intent

        url = i.getStringExtra("url")
        val jsonurl = url + "?json=1"
        renderBlog(jsonurl)

        val btn_share = findViewById(R.id.blog_single_share) as Button
        btn_share.setOnClickListener({
            shareUrl( blogtitle + "\n" + url)
        })

        val btn_comment = findViewById(R.id.blog_single_comment_btn) as Button
        btn_comment.setOnClickListener({
            val tv_comment = findViewById(R.id.blog_single_comment_text) as TextView
            postComment(tv_comment.text.toString())
        })

    }

    fun renderBlog(jsonurl: String){
        APIController(VolleyService()).get_string(jsonurl,{
            response ->
            try{

                val jobj = JSONObject(response)
                val post = jobj.getJSONObject("post")
                blogtitle = post.getString("title")
                blogid = post.getString("id")
                date = post.getString("date")
                author = post.getJSONObject("author").getString("name")

                val textView_title = findViewById(R.id.blog_single_title) as TextView
                val textView_extra = findViewById(R.id.blog_single_extra) as TextView
                textView_title.text = Html.fromHtml(blogtitle)
                textView_extra.text = author + "   " + date.substring(0,10)

                var content = post.getString("content").replace("<img", "<img width=\"100%\"")
                var comments: String = ""
                val comjsonA: JSONArray = post.getJSONArray("comments")

                if (comjsonA.length()>0){
                    var i=0
                    for (i in 0..comjsonA.length()-1){
                        comments += "<b> ${comjsonA.getJSONObject(i).getString("name")} </b><br>"
                        comments += comjsonA.getJSONObject(i).getString("content")
                        comments +="<br><br>"
                    }
                    comments = "<br><br><br><br><center><h2>Comments</h2></center> $comments "
                    content= content + comments
                }

                val wv = findViewById(R.id.blog_single_web) as WebView
                wv.settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN
                wv.loadDataWithBaseURL(null, "<br>$content<br><br><br>", "text/html", "utf-8", null)


            }catch (j: JSONException){
                j.printStackTrace()
            }
        })
    }

    fun shareUrl(link : String){
        val sendIntent = Intent()
        sendIntent.action = Intent.ACTION_SEND
        sendIntent.putExtra(Intent.EXTRA_TEXT, link)
        sendIntent.type = "text/plain"
        startActivity(Intent.createChooser(sendIntent, "Share Blog Post"))
    }

    fun postComment(comment: String){
        val post_id = blogid.toInt()
        val username = URLEncoder.encode(DatabaseFunctions().getUserName(applicationContext), "utf-8")
        val useremail = URLEncoder.encode(DatabaseFunctions().getUserEmail(applicationContext), "utf-8")
        val comment2 = URLEncoder.encode(comment, "utf-8")

        val link = "http://blog.nisb.in/api/respond/submit_comment/?post_id=$post_id&name=$username&email=$useremail&content=$comment2"

        val btn_comment = findViewById(R.id.blog_single_comment_btn) as Button
        val tv_comment = findViewById(R.id.blog_single_comment_text) as TextView
        btn_comment.setText("Posting..")
        btn_comment.setEnabled(false)

        APIController(VolleyService()).get_string(link,{
            response ->
//            Toast.makeText(applicationContext,response.toString(),Toast.LENGTH_LONG).show()
            btn_comment.setText("Post Comment")
            btn_comment.setEnabled(true)
            tv_comment.text=""
            renderBlog(url+"?json=1")

        })


    }
}
