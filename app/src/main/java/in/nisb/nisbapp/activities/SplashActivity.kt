package `in`.nisb.nisbapp

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * Created by mridul on 20/3/17.
 */
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ExtraFunctions.setSBColor(window, Color.parseColor("#95a5a6"))

        var intent: Intent

        if (DatabaseFunctions().isUserLogged(applicationContext) || DatabaseFunctions().isGuestLogged(applicationContext))
            intent = Intent(this, MainActivity::class.java)

        intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

}
