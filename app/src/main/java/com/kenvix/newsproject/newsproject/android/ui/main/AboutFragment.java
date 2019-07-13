package com.kenvix.newsproject.newsproject.android.ui.main;

import android.view.View;
import android.widget.TextView;

import com.kenvix.newsproject.newsproject.android.ui.base.BaseFragment;
import com.kenvix.utils.android.annotation.ViewAutoLoad;

public class AboutFragment extends BaseFragment {
    //@ViewAutoLoad
    public TextView mainAboutMessage;

    @Override
    protected void onInitialize(View view) {

    }

    @Override
    protected int getFragmentContentLayout() {
        return 0;
    }

    @Override
    protected int getBaseActivityContainer() {
        return 0;
    }
}
