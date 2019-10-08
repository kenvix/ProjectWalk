package com.kenvix.walk.ui.recognizer

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.ScrollView
import android.support.v7.widget.Toolbar
import com.kenvix.utils.android.annotation.ViewAutoLoad
import com.kenvix.walk.ApplicationEnvironment
import com.kenvix.walk.R
import com.kenvix.walk.ui.base.BaseActivity

@SuppressLint("Registered")
class RecognizerActivity : BaseActivity() {
    @ViewAutoLoad lateinit var recognizerImage: ImageView
    @ViewAutoLoad lateinit var recognizerScroll: ScrollView
    @ViewAutoLoad lateinit var recognizerToolbar: Toolbar

    private lateinit var fileUri: Uri

    override fun onInitialize(savedInstanceState: Bundle?) {
        val contentUri = intent.getStringExtra("image_content_uri")

        if (contentUri != null) {
            fileUri = Uri.parse(contentUri)
        } else {
            fileUri = Uri.parse(ApplicationEnvironment.getRawResourceUri(R.raw.example_food))
        }

        recognizerImage.setImageURI(fileUri)
        recognizerToolbar.setNavigationOnClickListener { finish() }
    }

    companion object Info {
        @Suppress("MemberVisibilityCanBePrivate")
        private const val ACTIVITY_REQUEST_CODE = 0xa06

        @Suppress("unused")
        @JvmStatic
        @JvmOverloads
        fun startActivity(activity: Activity, code: Int, contentUri: String?) {
            val intent = Intent(activity, RecognizerActivity::class.java)
            intent.putExtra("image_content_uri", contentUri)
            activity.startActivityForResult(intent, code)
        }
    }

    override fun getBaseLayout(): Int = R.layout.activity_recognizer
    override fun getBaseContainer(): Int = R.id.recognizer_container
}
