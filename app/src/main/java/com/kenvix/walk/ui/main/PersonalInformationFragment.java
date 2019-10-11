package com.kenvix.walk.ui.main;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;

//import com.example.navigation.R;
import com.kenvix.utils.android.annotation.ViewAutoLoad;
import com.kenvix.walk.ApplicationEnvironment;
import com.kenvix.walk.R;
import com.kenvix.walk.ui.base.BaseFragment;
import com.kenvix.walk.ui.login.LoginActivity;
import com.kenvix.walk.ui.other.DisplayUtils;

public class PersonalInformationFragment extends BaseFragment {
    @ViewAutoLoad public ImageView personalInformationAvatar;

    @Override
    protected void onInitialize(@NonNull View view) {
        DisplayUtils.onPersonalInformationFragmentLoad(this);
        personalInformationAvatar.setImageURI(Uri.parse(ApplicationEnvironment.getRawResourceUri(R.raw.res_info)));
        personalInformationAvatar.setOnClickListener(v -> LoginActivity.startActivity(getActivity(), 0xf01));
    }

    @Override
    protected int getFragmentContentLayout() {
        return R.layout.fragment_personal_information;
    }

    @Override
    public int getBaseActivityContainer() {
        return R.id.main_fragment_container;
    }
}
