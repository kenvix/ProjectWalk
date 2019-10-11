cmake_minimum_required(VERSION 3.4.1)

include_directories(src/main/jni/include/)

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
        src/main/jni/display-utils.c
)

target_link_libraries(
        # Specifies the target library.
        main-lib

        # Links the target library to the log library
        # included in the NDK.
        ${log-lib} android -ljnigraphics
)