package com.kenvix.walk.ui.main

import android.net.Uri
import android.view.View
import android.webkit.WebView
import android.widget.ImageView

import com.kenvix.walk.R
import com.kenvix.walk.ui.base.BaseFragment
import com.kenvix.utils.android.annotation.ViewAutoLoad
import com.kenvix.walk.ApplicationEnvironment


internal class MainFragment : BaseFragment() {
    @ViewAutoLoad lateinit var personalInformationAvatar: ImageView

    override fun onInitialize(view: View) {
        personalInformationAvatar.setImageURI(Uri.parse(ApplicationEnvironment.getRawResourceUri(R.raw.res_info)))
    }

    override fun getFragmentContentLayout(): Int {
        return R.layout.fragment_main
    }

    override fun getBaseActivityContainer(): Int {
        return R.id.main_fragment_container
    }

}
