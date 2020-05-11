package com.example.zhkj.verticaltextview.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zhkj.verticaltextview.R;
import com.example.zhkj.verticaltextview.view.CircleProgressView;
import com.example.zhkj.verticaltextview.view.SunAnimationView;
import com.example.zhkj.verticaltextview.view.TipsDialog;

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

    @Override
    protected void onResume() {
        super.onResume();
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
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TipsDialog tipsDialog=new TipsDialog(SunshineActivity.this,R.style.dialog,"弹窗内容在这里显示",false);
                tipsDialog.setNegative("取消");
                tipsDialog.setPositive("我知道了");
                tipsDialog.show();
                tipsDialog.setListener(new TipsDialog.OnCloseListener() {
                    @Override
                    public void onClick(Dialog dialog, boolean confirm,String etContent) {
                        if (confirm){
                            Toast.makeText(SunshineActivity.this,"确认"+etContent,Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(SunshineActivity.this,"取消"+etContent,Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
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
