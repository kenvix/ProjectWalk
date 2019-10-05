package com.kenvix.walk.utils

import android.annotation.SuppressLint
import android.webkit.WebSettings
import android.webkit.WebView
import com.kenvix.walk.ApplicationEnvironment

class WebViewInitializer(private val webView: WebView) {
    private var extendUserAgent = "Kenvix Generic Android Client"

    @SuppressLint("SetJavaScriptEnabled")
    fun initDefaultWebSettings() {
        val webSettings = webView.settings

        webSettings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW

        webSettings.useWideViewPort = true //将图片调整到适合webview的大小
        webSettings.loadWithOverviewMode = true // 缩放至屏幕的大小
        webSettings.defaultTextEncodingName = "utf-8"//设置编码格式

        //允许js代码
        webSettings.javaScriptEnabled = true
        //允许SessionStorage/LocalStorage存储
        webSettings.domStorageEnabled = true

        //允许缓存，设置缓存位置
        webSettings.setAppCacheEnabled(true)
        webSettings.setAppCachePath(ApplicationEnvironment.appContext.cacheDir.path)

        //允许WebView使用File协议
        webSettings.allowFileAccess = true

        //设置UA
        webSettings.userAgentString = webSettings.userAgentString + extendUserAgent

        //自动加载图片
        webSettings.loadsImagesAutomatically = true
    }

    fun disableZoom() {
        val webSettings = webView.settings

        //禁用放缩
        webSettings.displayZoomControls = false
        webSettings.builtInZoomControls = false
        //禁用文字缩放
        webSettings.textZoom = 100
    }

    fun enableFirstLoadAnime() {
        //TODO: Loading Anime
    }

    fun loadUrl(url: String) {
        webView.loadUrl(url)
    }
}