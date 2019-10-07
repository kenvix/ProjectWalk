package com.kenvix.walk.ui.main

import android.view.KeyEvent
import android.view.View
import android.webkit.WebView
import com.kenvix.utils.android.annotation.ViewAutoLoad
import com.kenvix.walk.ApplicationProperties
import com.kenvix.walk.R
import com.kenvix.walk.ui.base.BaseFragment
import com.kenvix.walk.utils.WebViewInitializer

/**
 * 论坛
 */
class ForumFragment : BaseFragment() {
    @ViewAutoLoad lateinit var forumWebView: WebView

    private lateinit var webViewInitializer: WebViewInitializer

    override fun onInitialize(view: View) {
        webViewInitializer = WebViewInitializer(forumWebView)
        webViewInitializer.setupWithCommonConfig(ApplicationProperties.ForumUrl)
    }

    //转发给 webViewInitializer
    fun onKeyDown(keyCode: Int, event: KeyEvent?) = webViewInitializer.onKeyDownCallback(keyCode, event)
    override fun getFragmentContentLayout(): Int = R.layout.fragment_forum
    override fun getBaseActivityContainer(): Int = R.id.main_fragment_container
}
