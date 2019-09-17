package com.kenvix.walk

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.graphics.drawable.Drawable
import android.support.annotation.NonNull
import com.kenvix.utils.log.Logging
import com.kenvix.walk.utils.AndroidLoggingHandler

class ApplicationEnvironment : Application(), Logging {
    override fun getLogTag(): String = "ApplicationEnvironment"

    companion object Utils {
        @SuppressLint("StaticFieldLeak")
        @NonNull
        @JvmStatic
        lateinit var appContext: Context
            private set

        @SuppressLint("StaticFieldLeak")
        @NonNull
        @JvmStatic
        lateinit var rootContext: Context
            private set

        @JvmStatic
        fun getViewString(id: Int, vararg formatArgs: Any): String {
            return appContext.getString(id, *formatArgs)
        }

        @JvmStatic
        fun getViewString(id: Int): String {
            return appContext.getString(id)
        }

        @JvmStatic
        fun getViewColor(id: Int): Int {
            return appContext.getColor(id)
        }

        @JvmStatic
        fun getViewDrawable(id: Int): Drawable? {
            return appContext.getDrawable(id)
        }

        @JvmStatic
        val viewResources
            get() = appContext.resources

        @JvmStatic
        fun getPackageName(name: String) = BuildConfig.APPLICATION_ID + "." + name
    }

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        rootContext = baseContext

        AndroidLoggingHandler.applyToKenvixLogger()

        logger.finer("Application Initialized")
    }
}