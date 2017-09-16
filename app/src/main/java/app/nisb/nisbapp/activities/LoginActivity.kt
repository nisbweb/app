package app.nisb.nisbapp

import android.content.Intent
import kotlinx.android.synthetic.main.activity_login.*;
import app.nisb.nisbapp.volley.*
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.widget.Toast
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {

    // display toast
    fun toaster(msg : String){
        Toast.makeText(applicationContext,msg,Toast.LENGTH_SHORT).show()
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        DBFunc().makeTables(applicationContext)

        if (DBFunc().isGuestLogged(applicationContext) || DBFunc().isUserLogged(applicationContext)){
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }


        // when we click on login
        login_button.setOnClickListener({

            // get the right url
            val url="https://docs.google.com/spreadsheets/d/1SSCpKj3HQBrdm5L3Av0FjLptXeC_G_kjDUFYDIIN8tc"+
                    "/gviz/tq?tq=select%20*%20where%20H%20%3D%20"+ login_ieeeno.text.toString()

            // disable and show loggin in on the button
            // as getting reply may take time and users might click it again
            login_button.text="Logging in"
            login_button.isEnabled=false

            APIController(VolleyService()).get_string(url, {
                response ->
                try {
                    val json = JSONObject(response?.substring(47,response.length-2).toString())
                    val row = json.getJSONObject("table").getJSONArray("rows").getJSONObject(0).getJSONArray("c") as JSONArray

                    val name = row.getJSONObject(1).getString("v")
                    val email = row.getJSONObject(2).getString("v")
                    val mob = row.getJSONObject(3).getString("f")
                    val branch = row.getJSONObject(4).getString("v")
                    val sem = row.getJSONObject(5).getString("f")
                    val usn = row.getJSONObject(6).getString("v")
                    val ieeeno = row.getJSONObject(7).getString("f")
                    val cs = row.getJSONObject(8).getString("v")

                    if (login_email.text.toString().equals(email)){
                        toaster("Welcome to NISB App")
                        //update info in the db
                        //firebase token sent to realtime db

                        val userData: JSONObject = JSONObject()
                        userData.put("name",name)
                        userData.put("email", email)
                        userData.put("mob", mob)
                        userData.put("branch", branch)
                        userData.put("sem",sem)
                        userData.put("usn", usn)
                        userData.put("ieeeno", ieeeno)
                        userData.put("cs",cs)
                        DBFunc().doUserLogin(applicationContext,email,ieeeno,userData.toString())
                        ExtraFunctions.userTokenUpdate(applicationContext)

                        val intent = Intent(applicationContext, MainActivity::class.java)
                        startActivity(intent)

                    }
                    else
                        toaster("Please verify your credentials.")



                    login_button.text = "Login"
                    login_button.isEnabled=true

                } catch (j: JSONException) {
                    toaster(j.toString())
                    toaster("Invalid IEEE NO")
                    login_button.text = "Login"
                    login_button.isEnabled=true
                }
            }) // volley ends here

        }) //end onclick listener


    }
}
