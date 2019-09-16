package com.kenvix.walk.ui.main

import android.app.Activity
import android.content.Intent
import android.support.design.widget.BottomNavigationView

import com.kenvix.walk.R
import com.kenvix.walk.ui.base.BaseActivity
import com.kenvix.utils.android.annotation.ViewAutoLoad

class MainActivity : BaseActivity() {

    companion object {
        @Suppress("MemberVisibilityCanBePrivate")
        const val ACTIVITY_REQUEST_CODE = 0xac0000

        @Suppress("unused")
        @JvmStatic
        fun startActivity(activity: Activity): Unit {
            val intent = Intent(activity, MainActivity::class.java)
            activity.startActivityForResult(intent, ACTIVITY_REQUEST_CODE)
        }
    }

    @ViewAutoLoad
    lateinit var mainNavView: BottomNavigationView

    private val mainFragment: MainFragment by lazy(LazyThreadSafetyMode.NONE) { MainFragment() }

    override fun onInitialize() {

        foregroundFragment = mainFragment
    }

    override fun getBaseLayout(): Int {
        return R.layout.activity_main
    }

    override fun getBaseContainer(): Int {
        return R.id.main_container
    }


}
