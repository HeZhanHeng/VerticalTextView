package com.example.zhkj.verticaltextview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.zhkj.verticaltextview.view.SunAnimationView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SunshineActivity extends AppCompatActivity {
    Button button;
    SunAnimationView sumView;
    private String mCurrentTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sunshine);
        initView();
    }

    private void initView() {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        mCurrentTime = simpleDateFormat.format(date);
        sumView = (SunAnimationView) findViewById(R.id.sun_view);
        sumView.setTimes("06:00", "18:40", mCurrentTime);
        button=findViewById(R.id.btn_set_time);
        button.setText("当前时间：" + mCurrentTime);
    }
}
