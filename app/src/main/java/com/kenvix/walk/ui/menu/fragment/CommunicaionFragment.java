package com.kenvix.walk.ui.menu.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;


import com.kenvix.walk.R;
import com.kenvix.walk.ui.base.BaseActivity;

public class CommunicaionFragment extends BaseActivity {
    @Override
    protected void onInitialize(@Nullable Bundle savedInstanceState) {

    }

    @Override
    protected int getBaseLayout() {
        return R.layout.fragment_communication;
    }

    @Override
    protected int getBaseContainer() {
        return 0; //TODO
    }
}
