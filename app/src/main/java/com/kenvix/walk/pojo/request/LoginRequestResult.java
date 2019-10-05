package com.kenvix.walk.pojo.request;

import com.kenvix.walk.pojo.common.LoginResult;
import com.kenvix.walk.pojo.common.UserInfo;

public class LoginRequestResult extends RequestResult<LoginResult> {
    private LoginResult data;

    @Override
    public LoginResult getData() {
        return data;
    }

    @Override
    public void setData(LoginResult data) {
        this.data = data;
    }
}