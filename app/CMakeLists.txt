cmake_minimum_required(VERSION 3.4.1)
include_directories(src/main/jni/include/)
add_library( # Sets the name of the library.
                          main-lib

                          # Sets the library as a shared library.
                          SHARED

                          # Provides a relative path to your source file(s).
                          src/main/jni/jni-main.c )

target_link_libraries( # Specifies the target library.
                       main-lib
                       # Links the target library to the log library
                       # included in the NDK.
                       ${log-lib} )