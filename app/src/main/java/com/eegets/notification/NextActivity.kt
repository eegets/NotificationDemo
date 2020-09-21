package com.eegets.notification

import android.net.http.SslError
import android.os.Build
import android.os.Bundle
import android.webkit.SslErrorHandler
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_next.*

/**
 * @packageName: com.eegets.notification
 * @author: eegets
 * @date: 20-9-14 下午2:14
 * @description: TODO
 */
class NextActivity :AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_next)

        setWebView()
    }

    private fun setWebView() {
        val webSetting = webview.settings
        webSetting.javaScriptEnabled = true
        webview.webViewClient = object: WebViewClient(){
            override fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?) {
                super.onReceivedSslError(view, handler, error)
                handler?.proceed()
            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSetting.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
            webSetting.loadWithOverviewMode = true
            webSetting.domStorageEnabled = true
            webSetting.blockNetworkImage = false
            webSetting.useWideViewPort = true
        }
        webview.loadUrl("https://www.baidu.com/")
    }
}