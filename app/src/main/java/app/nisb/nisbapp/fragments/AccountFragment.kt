package app.nisb.nisbapp.fragments

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import app.nisb.nisbapp.DBFunc
import app.nisb.nisbapp.LoginActivity
import app.nisb.nisbapp.MainActivity
import app.nisb.nisbapp.R
import kotlinx.android.synthetic.main.fragment_account.*;
import org.json.JSONObject

/**
 * Created by mridul on 6/24/17.
 */
class AccountFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment


        return inflater!!.inflate(R.layout.fragment_account, container, false)

    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        if (DBFunc().isUserLogged(context)){
            val jo : JSONObject = DBFunc().getUserData(context)
            account_name.text = "Name : " + jo.getString("name")
            account_ieeeno.text = "IEEE No : " + jo.getString("ieeeno")
            account_email.text = "Email : " + jo.getString("email")
            account_mobile.text = "Mobile : " + jo.getString("mob")
            account_branch.text = "Branch : " + jo.getString("branch")
            account_sem.text = "Semester : " + jo.getString("sem")
            account_usn.text = "USN : " + jo.getString("usn")
            account_cs.isChecked = (jo.getString("cs").equals("yes"))

            account_qr.setOnClickListener({
                val alert = AlertDialog.Builder(context)
                alert.setTitle("Member Details")

                val wv = WebView(context)

                val jo = DBFunc().getUserData(context)

                wv.loadUrl("http://chart.apis.google.com/chart?cht=qr&chs=300x300&chl=" + jo!!.toString())

                wv.setWebViewClient(object : WebViewClient() {
                    override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                        view.loadUrl(url)
                        return true
                    }
                })

                alert.setView(wv)
                alert.setNegativeButton("Done", DialogInterface.OnClickListener { dialog, id -> dialog.dismiss() })
                alert.show()
            })

        }


        account_logout!!.setOnClickListener({
            //clear user db

            DBFunc().doGuestLogout(context)
            DBFunc().doUserLogout(context)
            val intent = Intent(context, LoginActivity::class.java)
            startActivity(intent)

        })
    }

    public fun newInstance(text: String): AccountFragment {

        val f = AccountFragment()
        val b = Bundle()
        b.putString("msg", text)

        f.setArguments(b)

        return f
    }

}// Required empty public constructor
