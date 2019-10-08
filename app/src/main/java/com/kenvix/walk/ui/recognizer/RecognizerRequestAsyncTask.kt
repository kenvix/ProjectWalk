//--------------------------------------------------
// Class RecognizerRequestAsyncTask
//--------------------------------------------------
// Written by Kenvix <i@kenvix.com>
//--------------------------------------------------

package com.kenvix.walk.ui.recognizer

import android.net.Uri
import com.google.gson.Gson
import com.kenvix.walk.ApplicationEnvironment
import com.kenvix.walk.ApplicationProperties
import com.kenvix.walk.R
import com.kenvix.walk.exception.InvalidAuthorizationException
import com.kenvix.walk.pojo.request.LoginRequestResult
import com.kenvix.walk.ui.base.BaseAsyncTask
import okhttp3.FormBody
import okhttp3.MediaType
import okhttp3.Request
import okhttp3.RequestBody
import java.io.File
import java.io.IOException
import java.lang.IllegalArgumentException

class RecognizerRequestAsyncTask : BaseAsyncTask<Uri, Void, LoginRequestResult>() {
    override fun doTask(vararg params: Uri?): LoginRequestResult {
        if (params.isNotEmpty()) {
            val uri = params[0]

            if (uri != null) {
                val mediaType = MediaType.parse("application/octet-stream")
                val requestBody: RequestBody = RequestBody.create(mediaType, File(uri.path))

                val request = Request.Builder().put(requestBody)
                        .url(ApplicationProperties.getServerApiUrl("Recognizer/Food"))
                        .build()

                ApplicationEnvironment.okHttpClient.newCall(request).execute().use {
                    if (!it.isSuccessful)
                        throw IOException("Failed to send RecognizerRequest request: #${it.code()} ${it.message()?:"E"}")

                    if (it.body() == null)
                        throw IOException("Empty http body result")

                    return Gson().fromJson(it.body()!!.string(), LoginRequestResult::class.java)
                }
            }
        }

        throw IllegalArgumentException("Missing uri argument")
    }
}