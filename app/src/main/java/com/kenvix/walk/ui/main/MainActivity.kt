package com.kenvix.walk.ui.main

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.support.design.widget.BottomNavigationView

import com.kenvix.walk.R
import com.kenvix.walk.ui.base.BaseActivity
import com.kenvix.utils.android.annotation.ViewAutoLoad
import com.kenvix.utils.log.Logging
import com.kenvix.walk.utils.checkAndRequireRuntimePermissions
import com.kenvix.walk.utils.checkRequiredPermissionsCallback

class MainActivity : BaseActivity(), Logging {
    override fun getLogTag(): String = "MainActivity"

    companion object {
        @Suppress("MemberVisibilityCanBePrivate")
        const val ACTIVITY_REQUEST_CODE = 0xac0000
        const val PERMISSION_REQUEST_CODE = 0x2d

        @JvmStatic
        val REQUIRED_PERMISSIONS = arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
        )

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
        checkAndRequireRuntimePermissions(PERMISSION_REQUEST_CODE, *REQUIRED_PERMISSIONS)
        foregroundFragment = mainFragment
    }

    override fun getBaseLayout(): Int {
        return R.layout.activity_main
    }

    override fun getBaseContainer(): Int {
        return R.id.main_container
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        checkRequiredPermissionsCallback(PERMISSION_REQUEST_CODE, REQUIRED_PERMISSIONS, requestCode, permissions, grantResults)
    }
}
