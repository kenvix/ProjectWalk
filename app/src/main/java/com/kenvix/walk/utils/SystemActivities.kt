@file:JvmName("SystemActivities")
package com.kenvix.walk.utils

import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import com.kenvix.walk.ui.base.BaseActivity
import android.support.v4.content.FileProvider
import com.kenvix.walk.BuildConfig


const val ACTIVITY_REQUEST_CAMERA = 0xf01
const val ACTIVITY_REQUEST_ALBUM_IMAGE_SELECTOR = 0xf02

/**
 * 启动系统相机
 */
fun BaseActivity.startCameraActivity(): Uri {
    val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

    intent.flags = Intent.FLAG_GRANT_WRITE_URI_PERMISSION
    val tempFile = this.createTempFile()
    val contentUri = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID, tempFile)
    intent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri)

    this.startActivityForResult(intent, ACTIVITY_REQUEST_CAMERA)
    return contentUri
}

/**
 * 启动系统相册
 */
fun BaseActivity.startAlbumImageSelectorActivity() {
    val photoPickerIntent = Intent(Intent.ACTION_PICK, MediaStore.Audio.Media.EXTERNAL_CONTENT_URI)
    photoPickerIntent.type = "image/*"
    this.startActivityForResult(photoPickerIntent, ACTIVITY_REQUEST_ALBUM_IMAGE_SELECTOR)
}