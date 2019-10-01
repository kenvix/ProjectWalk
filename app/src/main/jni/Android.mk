LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)
LOCAL_MODULE    := main-lib
LOCAL_SRC_FILES := jni-main.c
LOCAL_EXPORT_LDLIBS := -llog
LOCAL_LDLIBS := -llog
LOCAL_LDFLAGS := -llog
LOCAL_SHARED_LIBRARIES += liblog
include $(BUILD_SHARED_LIBRARY)