package com.kenvix.walk.ui.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.kenvix.walk.R;
import com.kenvix.walk.ui.base.BaseActivity;

public class LoginActivity extends BaseActivity {
    /**
     * 按照规范，所有 Activity 都要定义一个独一无二的 Request code.
     * 在调用其他需要 Request code 的方法时传入它
     */
    private static final int ACTIVITY_REQUEST_CODE = 0xa08;

    @Override
    protected void onInitialize(@Nullable Bundle savedInstanceState) {

    }

    @Override
    protected int getBaseLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected int getBaseContainer() {
        return R.id.login_container;
    }

    /**
     * 启动这个 Activity
     * 按照规范，所有 Activity 都要定义这样的一个方法
     * @param fromActivity **来源** Activity 的 对象
     * @param requestCode **来源** Activity 的 Request code
     */
    @SuppressWarnings("unused")
    public static void startActivity(Activity fromActivity, int requestCode) {
        Intent requestIntent = new Intent(fromActivity, LoginActivity.class);
        fromActivity.startActivityForResult(requestIntent, requestCode);
    }
}
