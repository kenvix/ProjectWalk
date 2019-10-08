package com.kenvix.walk.ui.menu.fragment;

import android.support.annotation.NonNull;
import android.view.View;

//import com.example.navigation.R;
import com.kenvix.walk.R;
import com.kenvix.walk.ui.base.BaseFragment;

public class CapturePicturesFragment extends BaseFragment {

    @Override
    protected void onInitialize(@NonNull View view) {

    }

    @Override
    protected int getFragmentContentLayout() {
        return R.layout.fragment_capture_pictures;
    }

    @Override
    public int getBaseActivityContainer() {
        return 0; //TODO
    }
}
