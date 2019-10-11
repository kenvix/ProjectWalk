//--------------------------------------------------
// Class RecognizerRequestAsyncTask
//--------------------------------------------------
// Written by Kenvix <i@kenvix.com>
//--------------------------------------------------

package com.kenvix.walk.ui.login

import com.google.gson.Gson
import com.kenvix.walk.ApplicationEnvironment
import com.kenvix.walk.ApplicationProperties
import com.kenvix.walk.R
import com.kenvix.walk.exception.InvalidAuthorizationException
import com.kenvix.walk.pojo.request.LoginRequestResult
import com.kenvix.walk.ui.base.BaseAsyncTask
import okhttp3.FormBody
import okhttp3.Request
import java.io.IOException
import java.lang.Exception
import java.lang.IllegalArgumentException

class LoginAsyncTask : BaseAsyncTask<UserPass, Void, LoginRequestResult, LoginActivity>() {
    override fun doTask(vararg params: UserPass?): LoginRequestResult {
        if (params.isNotEmpty()) {
            val userPass = params[0]

            if (userPass != null) {
                val loginPost = FormBody.Builder()
                        .add("name", userPass.name)
                        .add("password", userPass.password)
                        .add("verifyCode", userPass.code ?: "")
                        .build()

                val loginRequest = Request.Builder().put(loginPost)
                        .url(ApplicationProperties.getServerApiUrl("API/User/Login"))
                        .build()

                ApplicationEnvironment.okHttpClient.newCall(loginRequest).execute().use {
                    if (!it.isSuccessful) {
                        when(it.code()) {
                            401 -> throw InvalidAuthorizationException(ApplicationEnvironment.getViewString(R.string.prompt_wrong_auth))
                            else -> throw IOException("Failed to send login request: #${it.code()} ${it.message()?:"E"}")
                        }
                    }

                    if (it.body() == null)
                        throw IOException("Empty http body result")

                    val loginData = Gson().fromJson(it.body()!!.string(), LoginRequestResult::class.java)
                    if (loginData.data.token.isNullOrEmpty())
                        throw IOException("Empty login result")

                    return loginData
                }
            }
        }

        throw IllegalArgumentException("Missing UserPass argument")
    }

    override fun onPostExecute(result: LoginRequestResult?) {
        super.onPostExecute(result)

        connection?.loginSubmit?.isClickable = true
        connection?.loginSubmit?.text = "登录"

        if (isExceptionThrown) {
            connection?.showAlertDialog(exception.localizedMessage, "登录失败") { }
        } else if (result != null) {
            connection?.showAlertDialog(result.data.name, "登录成功") {
                connection?.finish()
            }
        }
    }
}