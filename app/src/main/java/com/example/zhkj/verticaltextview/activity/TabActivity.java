package com.example.zhkj.verticaltextview.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.zhkj.verticaltextview.R;
import com.example.zhkj.verticaltextview.adapter.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class TabActivity extends AppCompatActivity {
private TabLayout mytab;
    ViewPager mViewPager;
    List<String> mTitle;
    List<View> viewList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        mytab = (TabLayout) findViewById(R.id.mytab);
        mViewPager = (ViewPager) findViewById(R.id.mViewPager);
        mTitle = new ArrayList<>();
        mTitle.add("选项卡一");
        mTitle.add("选项卡二");
        mTitle.add("选项卡三");
        mTitle.add("选项卡四");
//        mTitle.add("选项卡五");
//        mTitle.add("选项卡六");
        LayoutInflater inflater=LayoutInflater.from(this);
        View tab01 = inflater.inflate(R.layout.activity_goods_management_vp1, null);
        View tab02 = inflater.inflate(R.layout.activity_goods_management_vp2, null);
        View tab03 = inflater.inflate(R.layout.activity_goods_management_vp3, null);
        View tab04 = inflater.inflate(R.layout.activity_goods_management_vp4, null);
        viewList=new ArrayList<>();
        viewList.add(tab01);
        viewList.add(tab02);
        viewList.add(tab03);
        viewList.add(tab04);
        ViewPagerAdapter mAdapter=new ViewPagerAdapter(viewList,mTitle);
        mViewPager.setAdapter(mAdapter);
        mytab.setupWithViewPager(mViewPager);
    }
}
