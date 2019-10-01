package com.kenvix.walk;

final class JniMain {
    static {
        System.loadLibrary("opencv_java3");
        System.loadLibrary("main-lib");
    }

    private JniMain() {}

    public static native int onApplicationStart();
}