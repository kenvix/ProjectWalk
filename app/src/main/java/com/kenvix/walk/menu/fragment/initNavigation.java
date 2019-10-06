package com.kenvix.walk.menu.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.kenvix.walk.R;
import com.next.easynavigation.view.EasyNavigationBar;

import java.util.ArrayList;
import java.util.List;

public class initNavigation extends AppCompatActivity {
    private EasyNavigationBar navigationBar;
    private String[] tabText = {"首页", "识别","社区" ,"我的"};
    //未选中icon
    private int[] normalIcon = {R.mipmap.opinion, R.mipmap.camera,R.mipmap.communication, R.mipmap.person};
    //选中时icon
    private int[] selectIcon = {R.mipmap.opinion_test, R.mipmap.camera_test,R.mipmap.communication_test, R.mipmap.person_test};
    private List<Fragment> fragments = new ArrayList<>();
    private Handler mHandler = new Handler();
    private boolean flag = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigationBar = findViewById(R.id.main_fragment_container);

        fragments.add(new opinionFragment());
        fragments.add(new capturePicturesFragment());
        fragments.add(new communicaionFragment());
        fragments.add(new personalInformationFragment());

        navigationBar.titleItems(tabText)
                .normalIconItems(normalIcon)
                .selectIconItems(selectIcon)
                .fragmentList(fragments)
                .fragmentManager(getSupportFragmentManager())
                .iconSize(25)
                .tabTextSize(15)
                .tabTextTop(2)
                .normalTextColor(Color.parseColor("#666666"))   //Tab未选中时字体颜色
                .selectTextColor(Color.parseColor("#A670CECE"))   //Tab选中时字体颜色
                .scaleType(ImageView.ScaleType.CENTER_INSIDE)  //同 ImageView的ScaleType
                .build();
    }
}
