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
import android.widget.TextView
import com.kenvix.utils.android.annotation.ViewAutoLoad
import com.kenvix.walk.ApplicationEnvironment
import com.kenvix.walk.R
import com.kenvix.walk.pojo.request.RecognizerRequestResult
import com.kenvix.walk.ui.base.BaseActivity

@SuppressLint("Registered")
class RecognizerActivity : BaseActivity() {
    @ViewAutoLoad lateinit var recognizerImage: ImageView
    @ViewAutoLoad lateinit var recognizerScroll: ScrollView
    @ViewAutoLoad lateinit var recognizerToolbar: Toolbar
    @ViewAutoLoad lateinit var recognizerTextCalorie: TextView
    @ViewAutoLoad lateinit var recognizerTextName: TextView
    @ViewAutoLoad lateinit var recognizerTextProbability: TextView

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

        val asyncTask = RecognizerRequestAsyncTask()
        asyncTask.connection = this
        asyncTask.execute(fileUri)
    }

    companion object Info {
        @Suppress("MemberVisibilityCanBePrivate")
        private const val ACTIVITY_REQUEST_CODE = 0xa06

        @Suppress("unused")
        @JvmStatic
        @JvmOverloads
        fun startActivity(activity: Activity, code: Int, contentUri: String? = null) {
            val intent = Intent(activity, RecognizerActivity::class.java)
            intent.putExtra("image_content_uri", contentUri)
            activity.startActivityForResult(intent, code)
        }
    }

    fun onRecognizerCompleted(result: RecognizerRequestResult?) {
        if (result != null) {
            if (result.status != 0)
                return onRecognizerFailed(result.info)

            val foods = result.data.result

            if (foods.size <= 0)
                return onRecognizerFailed("抱歉，暂时不支持识别此菜品")

            recognizerTextName.text = foods[0].name
            recognizerTextCalorie.text = foods[0].calorie
            recognizerTextProbability.text = foods[0].probability
        } else {
            onRecognizerFailed("接口返回结果不正确")
        }
    }

    fun onRecognizerFailed(message: String) {
        showAlertDialog(message, "检测食物失败") {
            finish()
        }
    }

    override fun getBaseLayout(): Int = R.layout.activity_recognizer
    override fun getBaseContainer(): Int = R.id.recognizer_container
}
