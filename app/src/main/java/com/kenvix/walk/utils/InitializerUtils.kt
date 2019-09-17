@file:JvmName("InitializerUtils")

package com.kenvix.walk.utils

import android.Manifest
import android.app.Activity
import android.app.Application
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import com.kenvix.walk.ApplicationEnvironment
import com.kenvix.walk.R
import com.kenvix.walk.ui.base.BaseActivity
import com.kenvix.walk.ui.main.MainActivity

fun BaseActivity.checkAndRequireRuntimePermissions(code: Int, vararg permissions: String) {
    val wantedPermissions = mutableListOf<String>()

    for (permission in permissions) {
        ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED
        wantedPermissions.add(permission)
    }

    ActivityCompat.requestPermissions(this, permissions, code)
}

@JvmOverloads
fun BaseActivity.checkRequiredPermissionsCallback(wantedCode: Int,
                                                  wantedPermissions: Array<String>,
                                                  requestCode: Int,
                                                  permissions: Array<out String>,
                                                  grantResults: IntArray,
                                                  onRefused: () -> Unit = { this.finish() }) {
    if (requestCode == wantedCode) {
        val nextTryPermissions = mutableListOf<String>()

        for (wantedPermission in wantedPermissions) {
            val index = permissions.indexOf(wantedPermission)

            if (index == -1 || grantResults[index] == PackageManager.PERMISSION_DENIED)
                nextTryPermissions.add(wantedPermission)
        }

        if (nextTryPermissions.isNotEmpty()) {
            confirmDialog(getString(R.string.required_permission_denied, nextTryPermissions.joinToString("\n"))) {
                if (it)
                    checkAndRequireRuntimePermissions(wantedCode, *nextTryPermissions.toTypedArray())
                else
                    onRefused()
            }
        }
    }
}