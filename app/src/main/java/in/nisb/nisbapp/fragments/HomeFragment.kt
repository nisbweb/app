package `in`.nisb.nisbapp

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.TextView

/**
 * Created by mridul on 19/3/17.
 */

class HomeFragment : Fragment() {

    internal var view: View? =null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        view = inflater!!.inflate(R.layout.activity_home, container, false)

        val gr = view?.findViewById(R.id.home_user_greet) as TextView
        if (DatabaseFunctions().isUserLogged(context) == true)
            gr.text = "You are Logged in as " + DatabaseFunctions().getUserName(context)
        if (DatabaseFunctions().isGuestLogged(context) == true)
            gr.text = "You are Logged in as Guest"


        //about button
        val btn_about = view?.findViewById(R.id.home_about) as Button
        btn_about.setOnClickListener {
            val i = Intent(context, AboutActivity::class.java)
            startActivity(i)
        }

        //contact button
        val btn_contact = view?.findViewById(R.id.home_contact) as Button
        btn_contact.setOnClickListener {
            val i = Intent(context, ContactActivity::class.java)
            startActivity(i)
        }

        //Account button
        val btn_account = view?.findViewById(R.id.home_account) as Button
        btn_account.setOnClickListener {
            val i = Intent(context, AccountActivity::class.java)
            startActivity(i)
        }

        //Ankura button
        val btn_ankura = view?.findViewById(R.id.home_ankura) as Button
        btn_ankura.setOnClickListener {
            val i = Intent(context, AnkuraActivity::class.java)
            startActivity(i)
        }

        val wv_updates = view?.findViewById(R.id.home_update_web) as WebView
        wv_updates.settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN
        wv_updates.setWebViewClient(WebViewClient())
        wv_updates.loadUrl("http://nisb.in/jsonupdates")

        return view
    }
}
