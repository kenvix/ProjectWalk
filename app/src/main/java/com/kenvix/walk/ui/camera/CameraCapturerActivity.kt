//--------------------------------------------------
// Class CameraCapturerActivity
//--------------------------------------------------
// Written by Kenvix <i@kenvix.com>
//--------------------------------------------------

package com.kenvix.walk.ui.camera

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import com.kenvix.utils.android.annotation.ViewAutoLoad
import com.kenvix.walk.R
import com.kenvix.walk.ui.base.BaseActivity
import com.kenvix.walk.utils.*
import java.io.File


class CameraCapturerActivity : BaseActivity() {
    private lateinit var fileUri: Uri
    private lateinit var loadingAlertDialog: android.app.AlertDialog
    @ViewAutoLoad
    lateinit var cameraCapturerImage: ImageView

    override fun onInitialize(savedInstanceState: Bundle?) {
        checkAndRequireRuntimePermissions(PERMISSION_REQUEST_CODE, *REQUIRED_PERMISSIONS)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when(requestCode) {
            ACTIVITY_REQUEST_CAMERA, ACTIVITY_REQUEST_ALBUM_IMAGE_SELECTOR -> {
                loadingAlertDialog.dismiss()
                cameraCapturerImage.setImageURI(fileUri)

            }
            else -> logger.warning("Unknown activity request code $requestCode")
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        checkRequiredPermissionsCallback(PERMISSION_REQUEST_CODE, REQUIRED_PERMISSIONS, requestCode, permissions, grantResults)
    }

    override fun onAllPermissionsGranted(code: Int) {
        super.onAllPermissionsGranted(code)

        when(From.values()[intent.getIntExtra("capture_from", 0)]) {
            From.Camera -> {
                loadingAlertDialog = showProgressDialog(getString(R.string.prompt_no_response),
                        true, getString(R.string.prompt_waiting_camera)) {
                    if (!it) finish()
                }

                fileUri = startCameraActivity()
            }
            From.Album -> {
                showProgressDialog(getString(R.string.prompt_waiting_file))

            }
        }
    }

    companion object Info {
        @Suppress("MemberVisibilityCanBePrivate")
        const val ACTIVITY_REQUEST_CODE = 0xa01
        const val PERMISSION_REQUEST_CODE = 0xb01

        @JvmStatic
        val REQUIRED_PERMISSIONS = arrayOf(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA
        )

        @Suppress("unused")
        @JvmStatic
        fun startActivity(activity: Activity, code: Int, from: From = From.Camera) {
            val intent = Intent(activity, CameraCapturerActivity::class.java)
            intent.putExtra("capture_from", from.ordinal)
            activity.startActivityForResult(intent, code)
        }
    }

    enum class From {
        Camera, Album
    }

    override fun getBaseLayout(): Int = R.layout.activity_camera_capturer
    override fun getBaseContainer(): Int = R.id.camera_capturer_container
}