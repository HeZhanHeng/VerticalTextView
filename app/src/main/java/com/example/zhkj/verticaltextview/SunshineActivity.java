package com.example.zhkj.verticaltextview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.zhkj.verticaltextview.view.CircleProgressView;
import com.example.zhkj.verticaltextview.view.SunAnimationView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SunshineActivity extends AppCompatActivity {
    Button button;
    private TextView tv_marquee;
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
        //        横向滚动的广告
        tv_marquee=(TextView) findViewById(R.id.tv_marquee);
        tv_marquee.setText("此段代码由河南省郑州市管城区国际陆港开发建设有限公司之中浩科技有限公司综合业务部一分部和战恒编写");
        tv_marquee.setSelected(true);
        circleView=(CircleProgressView)findViewById(R.id.circleView);
        circleView.setMax(100);
        int progress=50;
        String text ="当前温度"+progress+"C";
        circleView.setProgressAndText(progress, text);
    }
}
