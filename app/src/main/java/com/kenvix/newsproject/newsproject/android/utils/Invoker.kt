package com.kenvix.newsproject.newsproject.android.utils


import android.util.Log
import android.view.View

import com.kenvix.newsproject.newsproject.android.ApplicationRuntime
import com.kenvix.newsproject.newsproject.android.R
import com.kenvix.utils.android.PreprocessorName

import java.lang.reflect.InvocationTargetException

object Invoker {
    private val classLoader by lazy { Invoker::class.java.classLoader ?: Thread.currentThread().contextClassLoader }
    private val formChecker: Class<*> by lazy { classLoader.loadClass(ApplicationRuntime.getPackageName("FormChecker")) }
    private val viewToolset: Class<*> by lazy { classLoader.loadClass(ApplicationRuntime.getPackageName("ViewToolset")) }

    @JvmStatic
    fun invokeViewAutoLoader(targetRaw: Any): Boolean {
        return try {
            viewToolset.getMethod(PreprocessorName.getViewAutoLoaderMethodName(targetRaw.javaClass.canonicalName), Any::class.java)
                    .invoke(null, targetRaw) as Boolean
        } catch (ex: NoSuchMethodException) {
            Log.e("Invoker for Activity", "Invoker can't detect loader method, may cause NullPointerException: " + ex.message)
            false
        } catch (ex: InvocationTargetException) {
            Log.e("Invoker for Activity", "Target Loader throws a unexpected exception: " + ex.message)
            ex.printStackTrace()
            false
        } catch (ex: Exception) {
            Log.e("Invoker for Activity", "No such view auto loader generated: " + targetRaw.javaClass.canonicalName + " : " + ex.message)
            ex.printStackTrace()
            false
        }

    }

    @JvmStatic
    fun invokeViewAutoLoader(targetRaw: Any, targetView: View): Boolean {
        return try {
            viewToolset.getMethod(PreprocessorName.getViewAutoLoaderMethodName(targetRaw.javaClass.canonicalName), Any::class.java, View::class.java)
                    .invoke(null, targetRaw, targetView) as Boolean
        } catch (ex: NoSuchMethodException) {
            Log.e("Invoker for View", "Invoker can't detect loader method, may cause NullPointerException: " + ex.message)
            false
        } catch (ex: InvocationTargetException) {
            Log.e("Invoker for View", "No such view auto loader generated: " + targetRaw.javaClass.canonicalName + " : " + ex.message)
            ex.printStackTrace()
            false
        } catch (ex: Exception) {
            Log.e("Invoker for View", "Target Loader throws a unexpected exception: " + ex.message)
            ex.printStackTrace()
            false
        }

    }

    @JvmStatic
    fun invokeFormChecker(targetRaw: Any): Boolean {
        return try {
            formChecker.getMethod(PreprocessorName.getFormEmptyCheckerMethodName(targetRaw.javaClass.canonicalName), String::class.java, Any::class.java)
                    .invoke(null, getString(R.string.error_field_required), targetRaw) as Boolean
        } catch (ex: Exception) {
            Log.e("Invoker", "No such form checker generated: " + ex.message)
            ex.printStackTrace()
            false
        }

    }

    private fun getString(id: Int): String {
        return ApplicationRuntime.getViewString(id)
    }
}
