package `in`.nisb.nisbapp

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        ExtraFunctions.setSBColor(window, Color.parseColor("#95a5a6"))

    }
}
