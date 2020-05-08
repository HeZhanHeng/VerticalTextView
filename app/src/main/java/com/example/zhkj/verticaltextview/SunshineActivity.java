package com.example.zhkj.verticaltextview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.zhkj.verticaltextview.view.CircleProgressView;
import com.example.zhkj.verticaltextview.view.SunAnimationView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SunshineActivity extends AppCompatActivity {
    Button button;
    SunAnimationView sumView;
    private String mCurrentTime;
    private CircleProgressView circleView;

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
        sumView.setTimes("05:29", "19:16", mCurrentTime);
        button=findViewById(R.id.btn_set_time);
        button.setText("当前时间：" + mCurrentTime);
        circleView=(CircleProgressView)findViewById(R.id.circleView);
        circleView.setMax(100);
        int progress=50;
        String text ="当前温度"+progress+"C";
        circleView.setProgressAndText(progress, text);
    }
}
