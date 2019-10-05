package com.kenvix.walk.ui.login;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.kenvix.walk.R;
import com.kenvix.walk.ui.base.BaseActivity;

public class LoginActivity extends BaseActivity {
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
}
