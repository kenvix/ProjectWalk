//--------------------------------------------------
// Class ServiceBinder
//--------------------------------------------------
// Written by Kenvix <i@kenvix.com>
//--------------------------------------------------

package com.kenvix.walk.utils

import android.os.Binder
import com.kenvix.walk.services.BaseService

abstract class ServiceBinder<T: BaseService> : Binder() {
    abstract val service: T
}