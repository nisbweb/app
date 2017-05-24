package `in`.nisb.nisbapp

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient

class AnkuraActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ankura)

        ExtraFunctions.setSBColor(window, Color.parseColor("#95a5a6"))

        val webView = findViewById(R.id.ankura_web) as WebView
        webView.settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN
        webView.setWebViewClient(WebViewClient())
        webView.settings.javaScriptEnabled = true
        webView.loadUrl("http://ankura.nisb.in/")
    }
}
