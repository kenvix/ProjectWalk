package com.kenvix.walk;

final class JniMain {
    static {
        System.loadLibrary("main-lib");
    }

    private JniMain() {}

    public static native int onApplicationStart();
}