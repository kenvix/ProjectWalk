//--------------------------------------------------
// Class WalkCounterServiceConnection
//--------------------------------------------------
// Written by Kenvix <i@kenvix.com>
//--------------------------------------------------

package com.kenvix.walk.ui.main

import android.content.ComponentName
import android.content.ServiceConnection
import android.os.IBinder
import com.kenvix.walk.R
import com.kenvix.walk.services.WalkCounterService
import java.lang.IllegalStateException

internal class WalkCounterServiceConnection(val activity: MainActivity) : ServiceConnection {
    override fun onServiceDisconnected(name: ComponentName?) {

    }

    override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
        service as WalkCounterService.Binder

        if (!service.isServiceSuccessfullyInitialized) {
            if (service.serviceInitException is IllegalStateException) {
                activity.showAlertDialog(activity.getString(R.string.walk_sensor_not_found)) {
                    activity.unbindWalkCounter()
                    activity.finish()
                }
            } else {
                activity.showAlertDialog(activity.getString(R.string.sensor_failed)
                        + "\n" + service.serviceInitException!!.toString()) {
                    activity.unbindWalkCounter()
                    activity.finish()
                }
            }
        }
    }
}