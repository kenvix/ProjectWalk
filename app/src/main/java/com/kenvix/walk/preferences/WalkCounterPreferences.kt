package com.kenvix.walk.preferences

import com.kenvix.utils.lang.toUnit

object WalkCounterPreferences : AbstractPreferencesManager("walk_counter") {
    var initSystemCount: Float
        get() = preferences.getFloat(this::initSystemCount.name, -1F)
        set(value) = editor.putFloat(this::initSystemCount.name, value).toUnit()

    var currentSystemCount: Float
        get() = preferences.getFloat(this::currentSystemCount.name, -1F)
        set(value) = editor.putFloat(this::currentSystemCount.name, value).toUnit()

    var initSystemCountTime: Long
        get() = preferences.getLong(this::initSystemCountTime.name, 0)
        set(value) = editor.putLong(this::initSystemCountTime.name, value).toUnit()

    var lastResetSystemCountTime: Long
        get() = preferences.getLong(this::lastResetSystemCountTime.name, 0)
        set(value) = editor.putLong(this::lastResetSystemCountTime.name, value).toUnit()

    var totalStep: Float
        get() = preferences.getFloat(this::totalStep.name, 0F)
        set(value) = editor.putFloat(this::totalStep.name, value).toUnit()

    var currentTaskStep: Float
        get() = preferences.getFloat(this::currentTaskStep.name, 0F)
        set(value) = editor.putFloat(this::currentTaskStep.name, value).toUnit()

    var totalStrictStep: Float
        get() = preferences.getFloat(this::totalStrictStep.name, 0F)
        set(value) = editor.putFloat(this::totalStrictStep.name, value).toUnit()

    var currentTaskStrictStep: Float
        get() = preferences.getFloat(this::currentTaskStrictStep.name, 0F)
        set(value) = editor.putFloat(this::currentTaskStrictStep.name, value).toUnit()
}