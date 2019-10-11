//--------------------------------------------------
// Class RecognizeSelectorFragment
//--------------------------------------------------
// Written by Kenvix <i@kenvix.com>
//--------------------------------------------------

package com.kenvix.walk.ui.main

import android.view.View
import com.kenvix.walk.R
import com.kenvix.walk.ui.base.BaseFragment

class RecognizeSelectorFragment : BaseFragment() {
    override fun onInitialize(view: View) {

    }

    override fun getFragmentContentLayout(): Int = R.layout.fragment_recognize_selector
    override fun getBaseActivityContainer(): Int = R.id.main_fragment_container
}