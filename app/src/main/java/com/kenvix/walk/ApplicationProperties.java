package com.kenvix.walk;

public final class ApplicationProperties {
    private ApplicationProperties() { }

    public static final String ForumUrl = "https://x.kenvix.com:7352/";
    public static final String BaseServerUrl = "";
    public static final String RecognizerPath = "";
    public static final long OkHttpClientTimeout = 10;
    public static final long OkHttpClientCacheSize = 1000000000L;

    public static String getServerApiUrl(String path) {
        return BaseServerUrl + path;
    }
}
