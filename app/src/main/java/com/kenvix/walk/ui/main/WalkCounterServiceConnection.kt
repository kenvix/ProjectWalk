//--------------------------------------------------
// Class WalkCounterServiceConnection
//--------------------------------------------------
// Written by Kenvix <i@kenvix.com>
//--------------------------------------------------

package com.kenvix.walk.ui.main

import android.content.ComponentName
import android.content.ServiceConnection
import android.os.IBinder

internal class WalkCounterServiceConnection(val activity: MainActivity) : ServiceConnection {
    override fun onServiceDisconnected(name: ComponentName?) {

    }

    override fun onServiceConnected(name: ComponentName?, service: IBinder?) {

    }
}