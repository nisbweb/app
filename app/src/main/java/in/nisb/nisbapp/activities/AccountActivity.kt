package `in`.nisb.nisbapp

import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.TableLayout
import android.widget.TextView

import org.json.JSONException

class AccountActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)

        //set status bar color
        ExtraFunctions.setSBColor(window, Color.parseColor("#95a5a6"))

        if (DatabaseFunctions().isUserLogged(applicationContext)) {
            loadAccountInfo()
        } else {
            val tb = findViewById(R.id.account_table) as TableLayout
            tb.visibility = View.INVISIBLE
            tb.layoutParams = TableLayout.LayoutParams(1, 1)
        }

        val btn_logout = findViewById(R.id.account_logout) as Button
        btn_logout.setOnClickListener {
            DatabaseFunctions().doUserLogout(applicationContext)
            DatabaseFunctions().doGuestLogout(applicationContext)
            val i = Intent(applicationContext, LoginActivity::class.java)
            startActivity(i)
        }


    }

    private fun loadAccountInfo() {
        val tvName = findViewById(R.id.account_name) as TextView
        val tvEmail = findViewById(R.id.account_email) as TextView
        val tvIeeeno = findViewById(R.id.account_ieeeno) as TextView
        val tvMobile = findViewById(R.id.account_mobile) as TextView
        val tvBranch = findViewById(R.id.account_branch) as TextView
        val cbCS = findViewById(R.id.account_cs) as CheckBox

        try {
            val jo = DatabaseFunctions().getUserData(applicationContext)
            tvName.text = jo!!.getString("name")
            tvEmail.text = jo.getString("emailAddress")
            tvIeeeno.text = "" + jo.getInt("ieeeno")
            tvMobile.text = jo.getString("phone")
            tvBranch.text = jo.getString("branch")
            cbCS.isChecked = jo.getBoolean("isCS")
        } catch (e: JSONException) {
        }


    }
}
