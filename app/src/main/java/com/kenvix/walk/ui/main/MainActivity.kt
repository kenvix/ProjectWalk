package com.kenvix.walk.ui.main

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.ServiceConnection
import android.support.design.widget.BottomNavigationView

import com.kenvix.walk.R
import com.kenvix.walk.ui.base.BaseActivity
import com.kenvix.utils.android.annotation.ViewAutoLoad
import com.kenvix.walk.services.WalkCounterService
import com.kenvix.walk.utils.*

class MainActivity : BaseActivity() {
    @ViewAutoLoad
    lateinit var mainNavView: BottomNavigationView

    private lateinit var mainFragment: MainFragment
    private lateinit var serviceConnection: ServiceConnection

    override fun onInitialize() {
        checkAndRequireRuntimePermissions(PERMISSION_REQUEST_CODE, *REQUIRED_PERMISSIONS)
        mainFragment = MainFragment()
        foregroundFragment = mainFragment

        serviceConnection = WalkCounterServiceConnection(this)
        logger.finest("MainActivity Initialized")
    }

    override fun getBaseLayout(): Int {
        return R.layout.activity_main
    }

    override fun getBaseContainer(): Int {
        return R.id.main_container
    }

    fun startWalkCounter() {
        startServiceInThreadPool(WalkCounterService::class.java)
    }

    fun bindWalkCounter() {
        bindService(WalkCounterService::class.java, serviceConnection)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        checkRequiredPermissionsCallback(PERMISSION_REQUEST_CODE, REQUIRED_PERMISSIONS, requestCode, permissions, grantResults)
    }

    companion object Info {
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
        fun startActivity(activity: Activity) {
            val intent = Intent(activity, MainActivity::class.java)
            activity.startActivityForResult(intent, ACTIVITY_REQUEST_CODE)
        }
    }
}
