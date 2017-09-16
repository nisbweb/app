package app.nisb.nisbapp

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import com.google.firebase.FirebaseApp


class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //FirebaseApp.initializeApp(applicationContext)
        startActivity(Intent(applicationContext, LoginActivity::class.java))
    }
}
