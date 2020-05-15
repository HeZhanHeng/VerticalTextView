package com.example.zhkj.verticaltextview.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.zhkj.verticaltextview.R;
import com.example.zhkj.verticaltextview.fragment.TestFragment;
import com.example.zhkj.verticaltextview.fragment.TestTwoFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class FrameActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tv_classify_one, tv_classify_two;
    private boolean checked;//默认值是false
    private FrameLayout container;
    private FragmentTransaction ft;
    private TestFragment testFragment;
    private TestTwoFragment testTwoFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame);
        container = findViewById(R.id.container);
        tv_classify_one = findViewById(R.id.tv_classify_one);
        tv_classify_one.setTextColor(getResources().getColor(R.color.colorAccent));
        tv_classify_two = findViewById(R.id.tv_classify_two);
        tv_classify_one.setOnClickListener(this);
        tv_classify_two.setOnClickListener(this);
        FragmentManager manager = getSupportFragmentManager();
        ft = manager.beginTransaction();
        testFragment = new TestFragment();
        testTwoFragment = new TestTwoFragment();
        ft.add(R.id.container, testFragment);
        ft.add(R.id.container, testTwoFragment);
        ft.show(testFragment).hide(testTwoFragment);
        ft.commit();
        Log.d("打印check默认值=",checked+"");
    }

    @Override
    public void onClick(View v) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft1 = manager.beginTransaction();
        switch (v.getId()) {
            case R.id.tv_classify_one:
                if (checked){
                    Map<String,String>map=new HashMap<>();
                    JSONObject jsonObject=new JSONObject();
                    try {
                        jsonObject.put("name","飞侠恒");
                        jsonObject.put("age","25");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    map.put("data",jsonObject.toString());
                    Log.e("map封装成json对象的数据=", map+"" );//{data={"name":"飞侠恒","age":"25"}}
                    tv_classify_one.setTextColor(getResources().getColor(R.color.colorAccent));
                    tv_classify_two.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                    checked=!checked;
                }

//                法一：每点击的时候进行加载数据
//                getSupportFragmentManager().beginTransaction().replace(R.id.container, new TestFragment()).commit();
//                法二：不需要重新加载数据，一次性加载
                ft1.show(testFragment).hide(testTwoFragment);
                break;
            case R.id.tv_classify_two:
                if (!checked){
                    Map<String,String>map=new HashMap<>();
                    JSONArray jsonArray=new JSONArray();
                    JSONObject jsonObject=new JSONObject();
                    try {
                        jsonObject.put("name","飞侠恒");
                        jsonObject.put("age","25");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    JSONObject jsonObject2=new JSONObject();
                    try {
                        jsonObject2.put("name","飞侠恒");
                        jsonObject2.put("age","26");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    jsonArray.put(jsonObject);
                    jsonArray.put(jsonObject2);
                    map.put("data", String.valueOf(jsonArray));
                    Log.e("map封装成json数组的数据=", map+"" );
                    tv_classify_one.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                    tv_classify_two.setTextColor(getResources().getColor(R.color.colorAccent));
                    checked=!checked;
                }
//                getSupportFragmentManager().beginTransaction().replace(R.id.container, new TestTwoFragment()).commit();
                ft1.show(testTwoFragment).hide(testFragment);
                break;
        }
        ft1.commit();
    }
}
