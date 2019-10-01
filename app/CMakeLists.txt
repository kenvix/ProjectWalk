cmake_minimum_required(VERSION 3.4.1)
#设置OpenCv的路径变量
set(pathToOpenCv E:\\RT\\OpenCV3-android-sdk)

include_directories(src/main/jni/include/)

#OpenCV
include_directories(${pathToOpenCv}/sdk/native/jni/include)
add_library(lib_opencv SHARED IMPORTED )
set_target_properties(
        lib_opencv
        PROPERTIES
        IMPORTED_LOCATION ${pathToOpenCv}/sdk/native/libs/${ANDROID_ABI}/libopencv_java3.so
)

#Android Log
find_library( # Sets the name of the path variable.
        log-lib

        # Specifies the name of the NDK library that
        # you want CMake to locate.
        log )

#Project main lib
add_library(
        # Sets the name of the library.
        main-lib

        # Sets the library as a shared library.
        SHARED

        # Provides a relative path to your source file(s).
        src/main/jni/jni-main.c
        src/main/jni/cv-utils.cpp
)

target_link_libraries(
        # Specifies the target library.
        main-lib

        # Links the target library to the log library
        # included in the NDK.
        ${log-lib} android -ljnigraphics lib_opencv
)