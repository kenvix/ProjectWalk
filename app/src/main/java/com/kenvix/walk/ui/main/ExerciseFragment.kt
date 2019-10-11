//--------------------------------------------------
// Class ExerciseFragment
//--------------------------------------------------
// Written by Kenvix <i@kenvix.com>
//--------------------------------------------------

package com.kenvix.walk.ui.main

import android.net.Uri
import android.view.View
import android.widget.ImageView
import com.kenvix.utils.android.annotation.ViewAutoLoad
import com.kenvix.walk.ApplicationEnvironment
import com.kenvix.walk.R
import com.kenvix.walk.ui.base.BaseFragment
import com.kenvix.walk.ui.other.DisplayUtils
import kotlinx.android.synthetic.main.activity_main.view.*

class ExerciseFragment: BaseFragment() {
    @ViewAutoLoad lateinit var exerciseAvatar: ImageView

    override fun onInitialize(view: View) {
        exerciseAvatar.setImageURI(Uri.parse(ApplicationEnvironment.getRawResourceUri(R.raw.res_exercise)))
    }

    override fun getFragmentContentLayout(): Int = R.layout.fragment_exercise
    override fun getBaseActivityContainer(): Int = R.id.main_fragment_container
}