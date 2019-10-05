package com.kenvix.walk.pojo.common;

public class LoginResult extends UserInfo {
    private String identifier;
    private String token;
    private int tokenExpireMinutes;

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getTokenExpireMinutes() {
        return tokenExpireMinutes;
    }

    public void setTokenExpireMinutes(int tokenExpireMinutes) {
        this.tokenExpireMinutes = tokenExpireMinutes;
    }
}
