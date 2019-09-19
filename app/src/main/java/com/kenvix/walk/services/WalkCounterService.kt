package com.kenvix.walk.services

import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.IBinder
import android.os.SystemClock
import com.kenvix.walk.preferences.WalkCounterPreferences
import com.kenvix.walk.utils.ServiceBinder


class WalkCounterService : BaseService() {
    private val binder = Binder()
    var initWalkStep: Float = -1F
        private set
    var currentWalkStep: Float = -1F
        private set
    var currentStrictWalkStep: Float = 0F
        private set
    private lateinit var sensorManager: SensorManager
    private lateinit var counterSensor: Sensor
    private lateinit var detectorSensor: Sensor
    private lateinit var detectorEventListener: DetectorEventListener
    private lateinit var counterEventListener: CounterEventListener

    override fun onInitialize() {
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        counterSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)
        detectorSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR)

        detectorEventListener = DetectorEventListener()
        counterEventListener = CounterEventListener()

        sensorManager.registerListener(detectorEventListener, detectorSensor, SensorManager.SENSOR_DELAY_FASTEST)
        sensorManager.registerListener(counterEventListener, counterSensor, SensorManager.SENSOR_DELAY_FASTEST)

        if (WalkCounterPreferences.initSystemCountTime <= 0) {
            WalkCounterPreferences.initSystemCountTime = System.currentTimeMillis()
        }
        currentStrictWalkStep = WalkCounterPreferences.currentTaskStrictStep
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        logger.finest("Walk counter started.")

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return binder
    }

    override fun onDestroy() {
        super.onDestroy()

        WalkCounterPreferences.commit()

        if (this::sensorManager.isInitialized) {
            if (this::detectorSensor.isInitialized)
                sensorManager.unregisterListener(detectorEventListener, detectorSensor)

            if (this::counterSensor.isInitialized)
                sensorManager.unregisterListener(counterEventListener, counterSensor)
        }
    }

    private inner class CounterEventListener : SensorEventListener {
        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) = Unit
        override fun onSensorChanged(event: SensorEvent?) {
            if (event != null) {
                logger.finest("Init walk detector changed to ${event.values}")

                val step = event.values[0]
                if (initWalkStep == -1F || initWalkStep >= step) {
                    initWalkStep = step

                    val lastReboot = System.currentTimeMillis() - SystemClock.elapsedRealtime()
                    if (lastReboot > WalkCounterPreferences.lastResetSystemCountTime) {
                        //System rebooted
                        WalkCounterPreferences.lastResetSystemCountTime = lastReboot

                        currentWalkStep += step
                    }

                    WalkCounterPreferences.initSystemCount = step
                    WalkCounterPreferences.currentSystemCount = step
                    WalkCounterPreferences.commit()
                } else {
                    currentWalkStep += step - initWalkStep
                    WalkCounterPreferences.currentSystemCount = step
                }
            }
        }
    }

    private inner class DetectorEventListener : SensorEventListener {
        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) = Unit
        override fun onSensorChanged(event: SensorEvent?) {
            if (event != null) {
                val step = event.values[0]
                currentStrictWalkStep += step
                WalkCounterPreferences.currentTaskStrictStep = currentStrictWalkStep
            }
        }
    }

    inner class Binder : ServiceBinder<WalkCounterService>() {
        val currentWalkStep
            get() = service.currentWalkStep
        val initWalkStep
            get() = service.initWalkStep

        override val service = this@WalkCounterService
    }
}