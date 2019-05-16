package com.example.zhkj.verticaltextview.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.zhkj.verticaltextview.R;

import java.util.ArrayList;

public class ThreeActivity extends AppCompatActivity {
ListView listView;
    private ArrayList<String> mData = new ArrayList<String>() {
        {
            for (int i = 0; i < 20; i++) {
                add("hello world, hello android " + i);
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);
        listView=(ListView)findViewById(R.id.listView);
    }
}
