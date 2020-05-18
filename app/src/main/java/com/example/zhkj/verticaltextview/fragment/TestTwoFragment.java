package com.example.zhkj.verticaltextview.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zhkj.verticaltextview.R;
import com.example.zhkj.verticaltextview.adapter.RecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2020/5/13 0013.
 */

public class TestTwoFragment extends Fragment {
    private TextView tv_hello2;
    private boolean isClick=false;
    private Activity mActivity;
    private RecyclerView recyclerView;
    private RecyclerAdapter adapter;
    private List<String>list;
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
        list=new ArrayList<>();
        for (int i=0;i<30;i++){
            list.add("第"+i+"条数据");
        }
        recyclerView=mActivity.findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(mActivity);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        GridLayoutManager gridLayoutManager=new GridLayoutManager(mActivity,2);
//        StaggeredGridLayoutManager staggeredGridLayoutManager=new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter=new RecyclerAdapter(mActivity,list);
        recyclerView.setAdapter(adapter);
        adapter.setOnTextClickListener(new RecyclerAdapter.OnTextClickListener() {
            @Override
            public void click(int pos) {
                Toast.makeText(mActivity,"点击"+pos,Toast.LENGTH_SHORT).show();
            }
        });
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
