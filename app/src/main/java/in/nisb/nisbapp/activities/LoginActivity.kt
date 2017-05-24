package `in`.nisb.nisbapp

import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

import org.json.JSONException
import org.json.JSONObject

import java.util.HashMap

import `in`.nisb.nisbapp.volley.APIController
import `in`.nisb.nisbapp.volley.VolleyService

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        ExtraFunctions.setSBColor(window, Color.parseColor("#95a5a6"))

        //if User is Logged in then goto main directly
        if (DatabaseFunctions().isGuestLogged(applicationContext) || DatabaseFunctions().isUserLogged(applicationContext))
            launchMain()

        //signin by authenticating from website
        val btn_signin = findViewById(R.id.login_signin) as Button
        btn_signin.setOnClickListener {
            val t_email = findViewById(R.id.login_email) as TextView
            val t_ieeeno = findViewById(R.id.login_ieeeno) as TextView
            authenticateUser(t_email.text.toString(), t_ieeeno.text.toString())
            btn_signin.text = "Loggin in.."
            btn_signin.isEnabled = false
            //Toast.makeText(getApplicationContext(),"Sign IN feature will soon be added.",Toast.LENGTH_SHORT).show();
        }

        //do guest login
        val tv_guest = findViewById(R.id.login_guest) as TextView
        tv_guest.setOnClickListener {
            DatabaseFunctions().doGuestLogin(applicationContext)
            launchMain()
        }
    }

    //get auth details from nisb.in
    private fun authenticateUser(email: String, ieeeno: String) {

        val REGISTER_URL = "http://nisb.in/appSignIn/"
        val params = HashMap<String, String>()
        params.put("email", email)
        params.put("ieeeno", ieeeno)

        APIController(VolleyService()).post_string(REGISTER_URL,params,{ response ->
            try{
                val jo: JSONObject = JSONObject(response)
                if (jo.getString("success").equals("true")){
                    val name = jo.getJSONObject("userData").getString("name")
                    val data = jo.getJSONObject("userData").toString()
                    DatabaseFunctions().doUserLogin(getApplicationContext(),email,ieeeno,name,data)
                    ExtraFunctions.userTokenUpdate(applicationContext)
                    launchMain()
                }
                else{
                    val btn_signin = (findViewById(R.id.login_signin)) as Button
                    btn_signin.setText("Login");
                    btn_signin.setEnabled(true);
                    Toast.makeText(getApplicationContext(),"Check the details.",Toast.LENGTH_LONG).show();
                }

            }catch(e :JSONException){}
        })

    }


    //Handle back Button
    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            val homeIntent = Intent(Intent.ACTION_MAIN)
            homeIntent.addCategory(Intent.CATEGORY_HOME)
            homeIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(homeIntent)
        }
        return super.onKeyDown(keyCode, event)
    }

    private fun launchMain() {
        val i = Intent(applicationContext, MainActivity::class.java)
        i.putExtra("context", "login")
        startActivity(i)
    }
}
