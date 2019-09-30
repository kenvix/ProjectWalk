// Rcon Manager for Android
// Copyright (c) 2019. Kenvix <i@kenvix.com>
//
// Licensed under GNU Affero General Public License v3.0

package com.kenvix.walk.ui.base;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.kenvix.utils.log.LogUtilsKt;
import com.kenvix.utils.log.Logging;
import com.kenvix.walk.R;
import com.kenvix.walk.utils.Invoker;

import java.util.function.Consumer;

public abstract class BaseActivity extends AppCompatActivity implements Logging {
    protected FragmentManager fragmentManager;
    private BaseFragment _foregroundFragment = null;
    private String logTag;

    @Override
    public String getLogTag() {
        return logTag == null ? (logTag = this.getClass().getSimpleName()) : logTag;
    }

    @Override
    protected final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getBaseLayout());
        fragmentManager = getSupportFragmentManager();
        Invoker.invokeViewAutoLoader(this);

        onInitialize(savedInstanceState);
    }

    public void showToast(String text, int toastLength) {
        Toast.makeText(this, text, toastLength).show();
    }

    public void showToast(String text) {
        showToast(text, Toast.LENGTH_LONG);
    }

    public void showSnackbar(View container, String text, int snackLength) {
        Snackbar.make(container, text, snackLength).show();
    }

    public void showSnackbar(View container, String text) {
        showSnackbar(container, text, Snackbar.LENGTH_SHORT);
    }

    public void showSnackbar(String text, int snackLength) {
        Snackbar.make(findViewById(getBaseContainer()), text, snackLength).show();
    }

    public void showSnackbar(String text) {
        showSnackbar(text, Snackbar.LENGTH_SHORT);
    }

    public void showExceptionToastPrompt(Throwable throwable) {
        showToast(getString(R.string.error_operation_failed) + throwable.getLocalizedMessage());
        LogUtilsKt.severe(getLogger(), throwable, "Global Exception Prompt: Operation FAILED");
    }

    public void showExceptionSnackbarPrompt(Throwable throwable) {
        showSnackbar(getString(R.string.error_operation_failed) + throwable.getLocalizedMessage(), Snackbar.LENGTH_LONG);
        LogUtilsKt.severe(getLogger(), throwable, "Global Exception Prompt: Operation FAILED");
    }

    public AlertDialog showConfirmDialog(String text, @Nullable String title, @Nullable Consumer<Boolean> callback) {
        return getConfirmBuilder(text, title, callback).show();
    }

    public AlertDialog showConfirmDialog(String text, @Nullable Consumer<Boolean> callback) {
        return getConfirmBuilder(text, null, callback).show();
    }

    public AlertDialog showAlertDialog(String text, @Nullable String title, @Nullable Consumer<Boolean> callback) {
        return getAlertBuilder(text, title, callback).show();
    }

    public AlertDialog showAlertDialog(String text, @Nullable Consumer<Boolean> callback) {
        return getAlertBuilder(text, null, callback).show();
    }

    public AlertDialog showAlertDialog(String text) {
        return getAlertBuilder(text, null, null).show();
    }

    public android.app.AlertDialog showProgressDialog(String text, boolean isCancelable, @Nullable String title, @Nullable Consumer<Boolean> callback) {
        return getProgressDialogBuilder(text, isCancelable, title, callback).show();
    }

    public android.app.AlertDialog showProgressDialog(String text) {
        return getProgressDialogBuilder(text, false, null, null).show();
    }

    public AlertDialog.Builder getAlertBuilder(String text, @Nullable String title, @Nullable Consumer<Boolean> callback) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setMessage(text)
                .setOnCancelListener(dialog -> {
                    if(callback != null)
                        callback.accept(false);
                })
                .setPositiveButton(getString(R.string.action_ok), (dialog, which) -> {
                    if (callback != null)
                        callback.accept(true);
                });

        if (title == null)
            return builder;
        else
            return builder.setTitle(title);
    }

    public AlertDialog.Builder getConfirmBuilder(String text, @Nullable String title, @Nullable Consumer<Boolean> callback) {
        return getAlertBuilder(text, title, callback)
                .setNegativeButton(getString(R.string.action_cancel),(dialog, which) -> {
                    if(callback != null)
                        callback.accept(false);
                });
    }

    public ProgressDialog.Builder getProgressDialogBuilder(String text, boolean isCancelable, @Nullable String title, @Nullable Consumer<Boolean> callback) {
        ProgressDialog.Builder builder = new ProgressDialog.Builder(this).setMessage(text);
        builder.setCancelable(isCancelable);

        if (isCancelable) {
            builder.setNegativeButton(getString(R.string.action_cancel),(dialog, which) -> {
                if(callback != null)
                    callback.accept(false);
            });
        }

        if (callback != null) {
            builder.setOnCancelListener(dialog -> callback.accept(false));
            builder.setOnDismissListener(dialog -> callback.accept(true));
        }

        if (title != null)
            builder.setTitle(title);
        return builder;
    }

    public final SharedPreferences getPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(this);
    }

    public final boolean setForegroundFragment(int container, BaseFragment fragment) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if(_foregroundFragment != null)
            transaction.hide(_foregroundFragment);

        _foregroundFragment = fragment;

        if(!fragment.isAdded()) {
            transaction.add(container, fragment);
        } else {
            transaction.show(fragment);
        }

        transaction.commit();
        return true;
    }

    public final boolean setForegroundFragment(BaseFragment fragment) {
        return setForegroundFragment(fragment.getBaseActivityContainer(), fragment);
    }

    public void onAllPermissionsGranted(int code) {

    }

    protected abstract void onInitialize(@Nullable Bundle savedInstanceState);
    protected abstract int getBaseLayout();
    protected abstract int getBaseContainer();

//    protected int getBaseFragmentContainer() {
//        throw new NotImplementedError("Called setForegroundFragment(fragment) but getBaseFragmentContainer() not implemented");
//    }

    public BaseFragment getForegroundFragment() {
        return _foregroundFragment;
    }
}
