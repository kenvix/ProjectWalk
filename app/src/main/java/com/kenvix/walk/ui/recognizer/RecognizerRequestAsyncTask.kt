//--------------------------------------------------
// Class RecognizerRequestAsyncTask
//--------------------------------------------------
// Written by Kenvix <i@kenvix.com>
//--------------------------------------------------

package com.kenvix.walk.ui.recognizer

import android.net.Uri
import com.google.gson.Gson
import com.kenvix.utils.log.severe
import com.kenvix.walk.ApplicationEnvironment
import com.kenvix.walk.ApplicationProperties
import com.kenvix.walk.pojo.request.RecognizerRequestResult
import com.kenvix.walk.ui.base.BaseAsyncTask
import okhttp3.*
import java.io.IOException
import java.lang.IllegalArgumentException

internal class RecognizerRequestAsyncTask : BaseAsyncTask<Uri, Void, RecognizerRequestResult, RecognizerActivity>() {
    override fun doTask(vararg params: Uri?): RecognizerRequestResult {
        if (params.isNotEmpty()) {
            val uri = params[0]

            if (uri != null) {
                val mediaType = MediaType.parse("application/octet-stream")
                val uploadStream = connection!!.contentResolver.openInputStream(uri)
                val requestBody: RequestBody = MultipartBody.Builder()
                        .setType(MultipartBody.FORM)
                        .addFormDataPart("file", "upload.jpg", RequestBody.create(mediaType, uploadStream!!.readBytes()))
                        .build()

                val request = Request.Builder()
                        .put(requestBody)
                        .url(ApplicationProperties.getServerApiUrl("Recognizer/Food"))
                        .build()

                ApplicationEnvironment.okHttpClient.newCall(request).execute().use {
                    if (!it.isSuccessful)
                        throw IOException("Failed to send RecognizerRequestResult request: #${it.code()} ${it.message()?:"E"}")

                    if (it.body() == null)
                        throw IOException("Empty http body result")

                    val body = it.body()!!.string()
                    return Gson().fromJson(body, RecognizerRequestResult::class.java)
                }
            }
        }

        throw IllegalArgumentException("Missing uri argument")
    }

    override fun onPostExecute(result: RecognizerRequestResult?) {
        super.onPostExecute(result)

        if (!isExceptionThrown) {
            connection?.onRecognizerCompleted(result)
        } else {
            connection?.onRecognizerFailed(exception.toString())
            logger.severe(exception)
        }
    }
}