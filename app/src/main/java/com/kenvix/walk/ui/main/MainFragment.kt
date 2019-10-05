package com.kenvix.walk.ui.main

import android.view.View
import android.webkit.WebView

import com.kenvix.walk.R
import com.kenvix.walk.ui.base.BaseFragment
import com.kenvix.utils.android.annotation.ViewAutoLoad


internal class MainFragment : BaseFragment() {

    override fun onInitialize(view: View) {

    }

    override fun getFragmentContentLayout(): Int {
        return R.layout.fragment_main
    }

    override fun getBaseActivityContainer(): Int {
        return R.id.main_fragment_container
    }

}
