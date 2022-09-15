package io.forsage.forsage

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.URLUtil
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private var wv: WebView? = null
    private val supportURL = "https://support.forsage.io/article/"
    private val mainURL = "https://busd.forsage.io"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tvSupport: TextView = findViewById(R.id.tv_support)

        tvSupport.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(supportURL)
            startActivity(intent)
        }

        wv = findViewById(R.id.wv)

        wv?.let { setupWebView(it) }
    }

    override fun onBackPressed() {
        wv?.let {
            if (it.canGoBack()) {
                it.goBack()
            } else {
                finish()
            }
        }
    }

    private fun setupWebView(wv: WebView) {
        wv.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {

                if (URLUtil.isNetworkUrl(request?.url.toString())) {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(request?.url.toString()))
                    startActivity(intent)
                    return false
                } else {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(request?.url.toString()))
                    startActivity(intent)
                }
                return true
            }
        }

        wv.settings.javaScriptEnabled = true
        wv.settings.domStorageEnabled = true
        wv.loadUrl(mainURL)
    }
}