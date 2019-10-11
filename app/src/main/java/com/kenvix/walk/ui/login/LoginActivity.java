package com.kenvix.walk.ui.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.kenvix.utils.android.annotation.ViewAutoLoad;
import com.kenvix.walk.ApplicationProperties;
import com.kenvix.walk.R;
import com.kenvix.walk.ui.base.BaseActivity;
import com.kenvix.walk.ui.other.WebViewActivity;

public class LoginActivity extends BaseActivity {
    @ViewAutoLoad public Button loginSubmit;
    @ViewAutoLoad public Button loginRegister;
    @ViewAutoLoad public TextView loginForgetPassword;
    @ViewAutoLoad public TextView loginPassword;
    @ViewAutoLoad public TextView loginUsername;

    /**
     * 按照规范，所有 Activity 都要定义一个独一无二的 Request code.
     * 在调用其他需要 Request code 的方法时传入它
     */
    private static final int ACTIVITY_REQUEST_CODE = 0xa08;

    @Override
    protected void onInitialize(@Nullable Bundle savedInstanceState) {
        loginSubmit.setOnClickListener(this::onLogin);
        loginForgetPassword.setOnClickListener(view -> showAPIPage("Home/User/ResetPassword"));
        loginRegister.setOnClickListener(view -> showAPIPage("Home/User/Register"));
    }

    private void showAPIPage(String url) {
        WebViewActivity.startActivity(this, ACTIVITY_REQUEST_CODE, ApplicationProperties.getServerApiUrl(url));
    }

    private void onLogin(View view) {
        if (loginUsername.getText().toString().isEmpty() || loginPassword.getText().toString().isEmpty()) {
            showAlertDialog("用户名或密码不能为空");
            return;
        }

        loginSubmit.setClickable(false);
        loginSubmit.setText("正在登录");
        UserPass userPass = new UserPass(loginUsername.getText().toString(), loginPassword.getText().toString(), null);

        LoginAsyncTask loginAsyncTask = new LoginAsyncTask();
        loginAsyncTask.setConnection(this);
        loginAsyncTask.execute(userPass);
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
