//--------------------------------------------------
// Class AbstractPreferencesManager
//--------------------------------------------------
// Written by Kenvix <i@kenvix.com>
//--------------------------------------------------

package com.kenvix.walk.preferences

import android.content.Context
import com.kenvix.walk.ApplicationEnvironment


abstract class AbstractPreferencesManager(val name: String, mode: Int = Context.MODE_PRIVATE) {
    val preferences = ApplicationEnvironment.appContext.getSharedPreferences(name, mode)
    val editor = preferences.edit()

    fun commit() = editor.commit()
}