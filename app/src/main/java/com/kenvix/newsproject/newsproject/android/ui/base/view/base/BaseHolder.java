// Rcon Manager for Android
// Copyright (c) 2019. Kenvix <i@kenvix.com>
//
// Licensed under GNU Affero General Public License v3.0

package com.kenvix.newsproject.newsproject.android.ui.base.view.base;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.kenvix.newsproject.newsproject.android.ui.base.BaseActivity;
import com.kenvix.newsproject.newsproject.android.utils.Invoker;

public abstract class BaseHolder<T> extends RecyclerView.ViewHolder {

    public BaseHolder(@NonNull View itemView) {
        super(itemView);
        Invoker.invokeViewAutoLoader(this, itemView);
    }

    protected BaseActivity getActivityByView(View v) {
        return (BaseActivity) v.getContext();
    }

    public abstract void bindView(T data);
}
