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
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zhkj.verticaltextview.R;
import com.example.zhkj.verticaltextview.adapter.ListViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2020/5/13 0013.
 */

public class TestFragment extends Fragment {
    private TextView tv_hello;
    private ListView listView;
    private List<String> list = new ArrayList<>();
    private boolean isClick = false;
    private Activity mActivity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getActivity()).inflate(R.layout.fragment_test, container, false);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mActivity = activity;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        Log.d("碎片1", "初始化");
    }

    private void initView() {
        tv_hello = mActivity.findViewById(R.id.tv_hello);
        tv_hello.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isClick = !isClick;
                if (isClick) {
                    tv_hello.setTextColor(getResources().getColor(R.color.colorAccent));
                } else {
                    tv_hello.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                }
            }
        });
        listView = getActivity().findViewById(R.id.listView);
        for (int i = 0; i < 20; i++) {
            list.add("这是第" + i + "条数据");
        }
        ListViewAdapter adapter = new ListViewAdapter(getActivity(), list);
        listView.setAdapter(adapter);
        adapter.setOnTvClickListener(new ListViewAdapter.OnTvClickListener() {
           @Override
           public void onClick(int pos) {
                 Toast.makeText(getActivity(),pos+"",Toast.LENGTH_SHORT).show();
           }
       });
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch (scrollState) {
                    case AbsListView.OnScrollListener.SCROLL_STATE_IDLE: // 没有滚动的时候
                        System.out.println("最后显示的listview的位置是--->>"
                                + listView.getLastVisiblePosition());
                        if (listView.getLastVisiblePosition() == (listView
                                .getCount() - 1)) {
                            Toast.makeText(getActivity(), "滑到listView底了",
                                    Toast.LENGTH_SHORT).show();

                        }
                        if (listView.getFirstVisiblePosition() == 0) {
                            Toast.makeText(getActivity(), "listView顶头了",
                                    Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case AbsListView.OnScrollListener.SCROLL_STATE_FLING:
                        System.out.println("SCROLL_STATE_FLING");
                        break;
                    case AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
                        System.out.println("SCROLL_STATE_TOUCH_SCROLL"
                                + "能看到的最后一个显示的位置--->>"
                                + listView.getLastVisiblePosition());
                        break;
                    default:

                        break;

                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
    }
}
