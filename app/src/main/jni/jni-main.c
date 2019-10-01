#include <android/log.h>
#include <string.h>
#include "include/com_kenvix_walk_JniMain.h"

#define LOG_TAG "NativeMain"
#define LogInfo(...) __android_log_print(ANDROID_LOG_INFO, LOG_TAG,__VA_ARGS__)
#define LogDebug(...) __android_log_print(ANDROID_LOG_DEBUG, LOG_TAG, __VA_ARGS__)
#define LogError(...) __android_log_print(ANDROID_LOG_ERROR, LOG_TAG,__VA_ARGS__)

JNIEXPORT jint JNICALL Java_com_kenvix_walk_JniMain_onApplicationStart(JNIEnv* env, jclass clazz) {
    LogDebug("Native Library Loaded");
    return 0;
}
