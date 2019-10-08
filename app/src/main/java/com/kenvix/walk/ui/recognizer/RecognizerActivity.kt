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
import android.view.View
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
    @ViewAutoLoad lateinit var recognizerTitle: TextView
    @ViewAutoLoad lateinit var recognizerTextCalorie: TextView
    @ViewAutoLoad lateinit var recognizerTextName: TextView
    @ViewAutoLoad lateinit var recognizerTextProbability: TextView
    @ViewAutoLoad lateinit var recognizerTextProblem0: TextView
    @ViewAutoLoad lateinit var recognizerTextProblem1: TextView
    @ViewAutoLoad lateinit var recognizerTextProblem2: TextView
    @ViewAutoLoad lateinit var recognizerTextProblem3: TextView

    private lateinit var loadingDialog: android.app.AlertDialog
    private lateinit var fileUri: Uri

    override fun onInitialize(savedInstanceState: Bundle?) {
        val contentUri = intent.getStringExtra("image_content_uri")
        recognizerTitle.text = getString(R.string.loading)
        loadingDialog = showProgressDialog("正在识别，请稍候 ...", true, null) {
            if(!it) finish()
        }

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
        loadingDialog.dismiss()

        if (result != null) {
            if (result.status != 0)
                return onRecognizerFailed(result.info)

            val foods = result.data.result

            if (foods.size <= 0)
                return onRecognizerFailed("抱歉，暂时不支持识别此菜品")

            recognizerScroll.visibility = View.VISIBLE
            recognizerTitle.text = getString(R.string.title_recognize_result)
            val i = 0

            recognizerTextName.text = foods[i].name
            recognizerTextCalorie.text = foods[i].calorie
            recognizerTextProbability.text = foods[i].probability

            if (foods[i].isHighSaltAndOil) {
                recognizerTextProblem0.setTextColor(getColor(R.color.warning))
                recognizerTextProblem0.text = "盐分和油脂含量过高"
                recognizerTextProblem1.visibility = View.VISIBLE
                recognizerTextProblem1.setTextColor(getColor(R.color.terminalText))
                recognizerTextProblem1.text = "这可能会增加患心血管疾病的风险，请考虑食用低盐食物"
            }

        } else {
            onRecognizerFailed("接口返回结果不正确")
        }
    }

    fun onRecognizerFailed(message: String) {
        loadingDialog.dismiss()

        showAlertDialog(message, "检测食物失败") {
            finish()
        }
    }

    override fun getBaseLayout(): Int = R.layout.activity_recognizer
    override fun getBaseContainer(): Int = R.id.recognizer_container
}
