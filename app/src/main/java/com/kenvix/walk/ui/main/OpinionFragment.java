package com.kenvix.walk.ui.main;

import android.support.annotation.NonNull;
import android.view.View;


import com.kenvix.walk.R;
import com.kenvix.walk.ui.base.BaseFragment;

public class OpinionFragment extends BaseFragment {
    @Override
    protected void onInitialize(@NonNull View view) {

    }

    @Override
    protected int getFragmentContentLayout() {
        return R.layout.fragment_opinion;
    }

    @Override
    public int getBaseActivityContainer() {
        return 0; //TODO
    }
}
