package com.kenvix.walk.menu.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
        return ;
    }
}
