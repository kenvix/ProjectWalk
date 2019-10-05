package com.kenvix.walk.ui.main

import android.view.KeyEvent
import android.view.View
import android.webkit.WebView
import com.kenvix.utils.android.annotation.ViewAutoLoad
import com.kenvix.walk.ApplicationProperties
import com.kenvix.walk.R
import com.kenvix.walk.ui.base.BaseFragment
import com.kenvix.walk.utils.WebViewInitializer

class ForumFragment : BaseFragment() {
    @ViewAutoLoad
    lateinit var forumWebView: WebView
    private lateinit var webViewInitializer: WebViewInitializer

    override fun onInitialize(view: View) {
        webViewInitializer = WebViewInitializer(forumWebView)

        webViewInitializer.enableFirstLoadAnime()
        webViewInitializer.initDefaultWebSettings()
        webViewInitializer.loadUrl(ApplicationProperties.ForumUrl)
        webViewInitializer.disableZoom()
    }

    fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && forumWebView.canGoBack()) {
            forumWebView.goBack()
            return true
        }

        return false
    }

    override fun getFragmentContentLayout(): Int = R.layout.fragment_forum
    override fun getBaseActivityContainer(): Int = R.id.main_fragment_container
}
