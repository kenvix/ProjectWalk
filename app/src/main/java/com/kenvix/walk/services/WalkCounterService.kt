package com.kenvix.walk.services

import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.IBinder
import com.kenvix.walk.utils.ServiceBinder


class WalkCounterService : BaseService() {
    private val binder = Binder()
    var walkStep: Long = 0L
        private set
    private lateinit var sensor: Sensor

    override fun onInitialize() {
        val sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        logger.finest("Walk counter started.")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return binder
    }

    inner class Binder : ServiceBinder<WalkCounterService>() {
        override val service = this@WalkCounterService
    }
}