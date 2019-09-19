@file:JvmName("ServiceUtils")
package com.kenvix.walk.utils

import android.content.Context.BIND_AUTO_CREATE
import android.content.ContextWrapper
import android.content.Intent
import android.content.ServiceConnection
import com.kenvix.walk.ApplicationEnvironment

fun ContextWrapper.startService(serviceClass: Class<*>) {
    val intent = Intent(this, serviceClass)
    this.startService(intent)
}

fun ContextWrapper.startServiceInThreadPool(serviceClass: Class<*>) {
    ApplicationEnvironment.cachedThreadPool.execute {
        startService(serviceClass)
    }
}

fun ContextWrapper.stopService(serviceClass: Class<*>) {
    val intent = Intent(this, serviceClass)
    this.stopService(intent)
}

@JvmOverloads
fun ContextWrapper.bindService(serviceClass: Class<*>, connection: ServiceConnection, flags: Int = BIND_AUTO_CREATE) {
    val intent = Intent(this, serviceClass)
    bindService(intent, connection, flags)
}