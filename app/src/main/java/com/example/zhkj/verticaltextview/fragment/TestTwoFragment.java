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

import com.example.zhkj.verticaltextview.R;

/**
 * Created by Administrator on 2020/5/13 0013.
 */

public class TestTwoFragment extends Fragment {
    private TextView tv_hello2;
    private boolean isClick=false;
    private Activity mActivity;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getActivity()).inflate(R.layout.fragment_test_two,container,false);
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
        Log.d("碎片2","初始化");
    }
    private void initView(){
        tv_hello2=mActivity.findViewById(R.id.tv_hello2);
        tv_hello2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isClick=!isClick;
                if (isClick){
                    tv_hello2.setTextColor(getResources().getColor(R.color.colorAccent));
                }else {
                    tv_hello2.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                }
            }
        });
    }
}
