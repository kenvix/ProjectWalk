 @file:JvmName("InitializerUtils")

package com.kenvix.walk.utils

import android.content.ContextWrapper
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import com.kenvix.walk.R
import com.kenvix.walk.ui.base.BaseActivity
import java.io.File

//fun BaseActivity.

val phoneCpuArchiture = System.getProperty("os.arch")

 /**
  * 创建临时文件
  */
fun ContextWrapper.createTempFile(prefix: String? = "temp_", suffix: String? = null): File {
    return File.createTempFile(prefix, suffix, cacheDir)
}

 /**
  * 检查并尝试获取运行时权限
  * @param code Request code
  *
  */
fun BaseActivity.checkAndRequireRuntimePermissions(code: Int, vararg permissions: String) {
    val wantedPermissions = mutableListOf<String>()

    for (permission in permissions) {
        ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED
        wantedPermissions.add(permission)
    }

    if (wantedPermissions.isEmpty())
        this.onAllPermissionsGranted(code)
    else
        ActivityCompat.requestPermissions(this, permissions, code)
}

 /**
  * 检查结果回调
  */
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
            showConfirmDialog(getString(R.string.required_permission_denied, nextTryPermissions.joinToString("\n"))) {
                if (it)
                    checkAndRequireRuntimePermissions(wantedCode, *nextTryPermissions.toTypedArray())
                else
                    onRefused()
            }
        } else {
            onAllPermissionsGranted(requestCode)
        }
    }
}