package com.kenvix.walk.ui.main

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.support.design.widget.BottomNavigationView
import android.view.KeyEvent
import android.view.MenuItem
import android.view.View
import android.widget.TextView

import com.kenvix.walk.R
import com.kenvix.walk.ui.base.BaseActivity
import com.kenvix.utils.android.annotation.ViewAutoLoad
import com.kenvix.utils.lang.toUnit
import com.kenvix.walk.services.WalkCounterService
import com.kenvix.walk.ui.login.LoginActivity
import com.kenvix.walk.ui.camera.CameraCapturerActivity
import com.kenvix.walk.ui.recognizer.RecognizerActivity
import com.kenvix.walk.utils.*

class MainActivity : BaseActivity() {
    @ViewAutoLoad lateinit var mainNavView: BottomNavigationView
    //@ViewAutoLoad lateinit var mainStepCount: TextView

    private lateinit var mainFragment: MainFragment
    private val forumFragment by lazy(LazyThreadSafetyMode.NONE) { ForumFragment() }
    private val exerciseFragment by lazy(LazyThreadSafetyMode.NONE) { ExerciseFragment() }
    private val personalFragment by lazy(LazyThreadSafetyMode.NONE) { PersonalInformationFragment() }

    private val serviceConnection by lazy(LazyThreadSafetyMode.NONE) { WalkCounterServiceConnection(this) }
    private val recognizerSelectorFragment by lazy(LazyThreadSafetyMode.NONE) { RecognizeSelectorFragment() }
    private var backClickTime: Long = 0

    override fun onInitialize(savedInstanceState: Bundle?) {
        checkAndRequireRuntimePermissions(PERMISSION_REQUEST_CODE, *REQUIRED_PERMISSIONS)
        mainFragment = MainFragment()

        foregroundFragment = mainFragment
        logger.finest("MainActivity Initialized")
        //startWalkCounter()
        //bindWalkCounter()
        //CameraCapturerActivity.startActivity(this, ACTIVITY_REQUEST_CODE, CameraCapturerActivity.From.Camera)
        //ForumFragment.startActivity(this, ACTIVITY_REQUEST_CODE)
        //RecognizerActivity.startActivity(this, ACTIVITY_REQUEST_CODE, null)

        mainNavView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.main_navigation_me -> { foregroundFragment = personalFragment; true }
                R.id.main_navigation_forum -> { foregroundFragment = forumFragment; true }
                R.id.main_navigation_index -> { foregroundFragment = mainFragment; true }
                R.id.main_navigation_exercise -> { foregroundFragment = exerciseFragment; true }
                R.id.main_navigation_recognition -> {
                    CameraCapturerActivity.startActivity(this, ACTIVITY_REQUEST_CODE)
                    false
                }
                else -> false
            }
        }
    }

    /**
     * 当按返回等特殊键时发生的事件
     */
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (foregroundFragment == forumFragment) {
            //转发给 forumFragment
            if (forumFragment.onKeyDown(keyCode, event))
                return true
        }

        if (System.currentTimeMillis() - backClickTime > BACK_WAIT_TIME) {
            showSnackbar(getString(R.string.prompt_back_again))
            backClickTime = System.currentTimeMillis()
            return true
        }

        return super.onKeyDown(keyCode, event)
    }

    override fun getBaseLayout(): Int = R.layout.activity_main
    override fun getBaseContainer(): Int = R.id.main_fragment_container

    fun startWalkCounter() {
        startServiceInThreadPool(WalkCounterService::class.java) {
            showAlertDialog(getString(R.string.walk_sensor_not_found))
        }
    }

    fun bindWalkCounter() {
        bindService(WalkCounterService::class.java, serviceConnection)
    }

    fun unbindWalkCounter() {
        unbindService(serviceConnection)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        checkRequiredPermissionsCallback(PERMISSION_REQUEST_CODE, REQUIRED_PERMISSIONS, requestCode, permissions, grantResults)
    }

    companion object Info {
        @Suppress("MemberVisibilityCanBePrivate")
        private const val ACTIVITY_REQUEST_CODE = 0xa00
        const val PERMISSION_REQUEST_CODE = 0xb00
        const val BACK_WAIT_TIME = 2000

        @JvmStatic
        val REQUIRED_PERMISSIONS = arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
        )

        @Suppress("unused")
        @JvmStatic
        fun startActivity(activity: Activity, code: Int) {
            val intent = Intent(activity, MainActivity::class.java)
            activity.startActivityForResult(intent, code)
        }
    }
}
