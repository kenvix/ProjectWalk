//--------------------------------------------------
// Class BaseService
//--------------------------------------------------
// Written by Kenvix <i@kenvix.com>
//--------------------------------------------------

package com.kenvix.walk.services

import android.app.Service
import com.kenvix.utils.log.Logging


abstract class BaseService : Service(), Logging {
    private lateinit var logTag: String

    override fun getLogTag(): String = logTag

    override fun onCreate() {
        super.onCreate()
        logTag = this.javaClass.simpleName
        onInitialize()

        logger.finest("Created service")
    }

    protected abstract fun onInitialize()
}