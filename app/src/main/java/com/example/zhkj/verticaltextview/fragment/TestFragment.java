package com.example.zhkj.verticaltextview.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zhkj.verticaltextview.R;

/**
 * Created by Administrator on 2020/5/13 0013.
 */

public class TestFragment extends Fragment {
    private TextView tv_hello;
    private boolean isClick=false;
    private Activity mActivity;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getActivity()).inflate(R.layout.fragment_test,container,false);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mActivity=activity;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        Log.d("碎片1","初始化");
    }
    private void initView(){
        tv_hello=mActivity.findViewById(R.id.tv_hello);
        tv_hello.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isClick=!isClick;
               if (isClick){
                   tv_hello.setTextColor(getResources().getColor(R.color.colorAccent));
               }else {
                   tv_hello.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
               }
            }
        });
    }
}
