package com.kenvix.walk.pojo.request;

import com.kenvix.walk.pojo.common.UserInfo;

public class UserInfoRequestResult extends RequestResult<UserInfo> {
    private UserInfo data;

    @Override
    public UserInfo getData() {
        return data;
    }

    @Override
    public void setData(UserInfo data) {
        this.data = data;
    }
}
