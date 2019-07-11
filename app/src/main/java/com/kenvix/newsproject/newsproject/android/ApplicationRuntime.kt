package com.kenvix.newsproject.newsproject.android

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.support.annotation.NonNull
import android.view.View

class ApplicationRuntime : Application() {

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
    }

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        rootContext = baseContext
    }
}