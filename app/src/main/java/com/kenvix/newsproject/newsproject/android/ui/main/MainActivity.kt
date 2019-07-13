package com.kenvix.newsproject.newsproject.android.ui.main

import android.support.design.widget.BottomNavigationView
import android.view.MenuItem
import android.widget.TextView

import com.kenvix.newsproject.newsproject.android.R
import com.kenvix.newsproject.newsproject.android.ui.base.BaseActivity
import com.kenvix.utils.android.annotation.ViewAutoLoad

class MainActivity : BaseActivity() {
    @ViewAutoLoad
    lateinit var mainMessage: TextView

    @ViewAutoLoad
    lateinit var mainNavView: BottomNavigationView

    internal val mainFragment: MainFragment by lazy(LazyThreadSafetyMode.NONE) { MainFragment() }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                mainMessage.setText(R.string.title_about)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                mainMessage.setText(R.string.title_about)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                mainMessage.setText(R.string.title_about)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onInitialize() {
        mainNavView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    override fun getBaseLayout(): Int {
        return R.layout.activity_main
    }

    override fun getBaseContainer(): Int {
        return 0
    }

}
