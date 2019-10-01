LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)
LOCAL_MODULE    := main-lib
LOCAL_SRC_FILES := jni-main.c
include $(BUILD_SHARED_LIBRARY)