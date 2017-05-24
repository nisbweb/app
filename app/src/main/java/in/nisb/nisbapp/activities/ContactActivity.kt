package `in`.nisb.nisbapp

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class ContactActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)

        //set status bar color
        ExtraFunctions.setSBColor(window, Color.parseColor("#95a5a6"))


        val btn_submit = findViewById(R.id.contact_submit) as Button
        btn_submit.setOnClickListener {
            val sub = (findViewById(R.id.contact_subject) as EditText).text.toString()
            val body = (findViewById(R.id.contact_body) as EditText).text.toString()

            val intent = Intent(Intent.ACTION_SENDTO)
            intent.data = Uri.parse("mailto:nisb.ieee@gmail.com") // only email apps should handle this
            intent.putExtra(Intent.EXTRA_EMAIL, "nisb.ieee@gmail.com")
            intent.putExtra(Intent.EXTRA_SUBJECT, sub)
            intent.putExtra(Intent.EXTRA_TEXT, body)
            if (intent.resolveActivity(packageManager) != null) {
                startActivityForResult(intent, 1)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1) {
            Toast.makeText(applicationContext, "We will get back to you! ", Toast.LENGTH_SHORT).show()
            (findViewById(R.id.contact_subject) as EditText).setText("")
            (findViewById(R.id.contact_body) as EditText).setText("")
        }
    }
}
