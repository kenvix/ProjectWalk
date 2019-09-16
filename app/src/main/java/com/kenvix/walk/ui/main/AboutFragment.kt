package com.kenvix.walk.ui.main

import android.view.View
import android.widget.TextView

import com.kenvix.walk.ui.base.BaseFragment

internal class AboutFragment : BaseFragment() {
    //@ViewAutoLoad
    var mainAboutMessage: TextView? = null

    override fun onInitialize(view: View) {

    }

    override fun getFragmentContentLayout(): Int {
        return 0
    }

    override fun getBaseActivityContainer(): Int {
        return 0
    }
}
