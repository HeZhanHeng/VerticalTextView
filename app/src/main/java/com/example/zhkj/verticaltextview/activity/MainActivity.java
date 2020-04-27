package com.example.zhkj.verticaltextview.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zhkj.verticaltextview.R;
import com.example.zhkj.verticaltextview.view.MarqueTextView;
import com.example.zhkj.verticaltextview.view.VerticalTextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;

import static org.xmlpull.v1.XmlPullParser.*;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, View.OnClickListener {
    private Activity mActivity;
    private ViewPager viewPager;
    private boolean isLooper;
    private List<View> viewList;//view数组
    private View view1, view2, view3,view4;
    private ViewGroup vg;//放置圆点
    //实例化原点View
    private ImageView iv_point;
    private ImageView[]ivPointArray;
    private VerticalTextView TextView;
    private MarqueTextView tv_marquee;
    private Button btn_jump,btn_jump2,btn_jump3,btn_jump4,btn_jump5;
    private TextView tv_handler;
    @SuppressLint("HandlerLeak")
    private Handler handler=new Handler(){
        public void handleMessage(android.os.Message msg){
            switch (msg.what){
                case 0x123:
                    int index=msg.arg1;
                    String obj= (String) msg.obj;
                    tv_handler.setText(index+"");
                    if (index==1){
                        tv_handler.setText(obj);
                    }
                    break;
            }
        }
    };
    private ArrayList<String> titleList= new ArrayList<String>();
    private LinearLayout level1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mActivity=MainActivity.this;
        init();
        //加载底部圆点
        initPoint();
    }
//    屏幕占满
//    @Override
//    public void onWindowFocusChanged(boolean hasFocus) {
//        super.onWindowFocusChanged(hasFocus);
//        if (hasFocus && Build.VERSION.SDK_INT >= 19) {
//            View decorView = getWindow().getDecorView();
//            decorView.setSystemUiVisibility(
//                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                            | View.SYSTEM_UI_FLAG_FULLSCREEN
//                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
//        }
//    }
    private void init() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        LayoutInflater inflater2 = getLayoutInflater();
        view1 = inflater2.inflate(R.layout.activity_goods_management_vp1, null);
        view2 = inflater2.inflate(R.layout.activity_goods_management_vp2, null);
        view3 = inflater2.inflate(R.layout.activity_goods_management_vp3, null);
        view4 = inflater2.inflate(R.layout.activity_goods_management_vp4, null);
        viewList = new ArrayList<View>();
        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view3);
        viewList.add(view4);
        PagerAdapter pagerAdapter = new PagerAdapter() {
            @Override
            public int getCount() {
                if (viewList != null) {
//                    return viewList.size();
//                    设置轮播最大值==无限轮播
                    return Integer.MAX_VALUE;
                }
                return 0;
            }

            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {
                return arg0 == arg1;
            }

            @Override
            public void destroyItem(ViewGroup container, int position,
                                    Object object) {
                //第三处修改，移除的索引为集合的长度
                int newPosition = position % viewList.size();
                container.removeView(viewList.get(newPosition));
                // TODO Auto-generated method stub
//                container.removeView(viewList.get(position));
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                //第二处修改，当前要显示的数据索引为集合长度
                int newPosition = position % viewList.size();
                container.addView(viewList.get(newPosition));
                return viewList.get(newPosition);
                // TODO Auto-generated method stub
//                container.addView(viewList.get(position));
//                return viewList.get(position);
            }
        };
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOnPageChangeListener(this);
        viewPager.setCurrentItem(0);
//        open thread for
        new Thread(new Runnable() {
            @Override
            public void run() {
                isLooper = true;
                while (isLooper) {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    mActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                        }
                    });
                }
            }
        }).start();

        TextView = findViewById(R.id.mTv);
        titleList.add("熬过最难熬的那段时间");
        titleList.add("你擦干眼泪终于明白");
        titleList.add("有些人");
        titleList.add("注定是让你成长的");
        titleList.add("正如");
        titleList.add("至尊宝在离开紫霞仙子后");
        titleList.add("才真正成长为孙悟空一样");
        TextView.setTextList(titleList);
        TextView.setText(14, 50, Color.BLACK);//设置属性
        TextView.setTextStillTime(5000);//设置停留时长间隔
        TextView.setAnimTime(500);//设置进入和退出的时间间隔
        TextView.setOnItemClickListener(new VerticalTextView.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(MainActivity.this, "点击了：" + titleList.get(position), Toast.LENGTH_SHORT).show();
            }
        });
//        横向滚动的广告
        tv_marquee=(MarqueTextView)findViewById(R.id.tv_marquee);
        tv_marquee.setText("此段代码由河南省郑州市管城区国际陆港开发建设有限公司之中浩科技有限公司综合业务部一分部和战恒编写");
        tv_marquee.setSelected(true);
        btn_jump=(Button)findViewById(R.id.btn_jump);
        tv_handler=(TextView)findViewById(R.id.tv_handler);
        btn_jump2=(Button)findViewById(R.id.btn_jump2);
        btn_jump3=(Button)findViewById(R.id.btn_jump3);
        btn_jump.setOnClickListener(this);
        btn_jump2.setOnClickListener(this);
        btn_jump3.setOnClickListener(this);
        btn_jump4=(Button)findViewById(R.id.btn_jump4);
        btn_jump4.setOnClickListener(this);
        btn_jump5=findViewById(R.id.btn_jump5);
        btn_jump5.setOnClickListener(this);
        level1 = (LinearLayout) findViewById(R.id.level1);
        level1.setOnClickListener(this);
    }
    /**
     * 加载底部圆点
     */
    private void initPoint() {
        //这里实例化LinearLayout
        vg = (ViewGroup) findViewById(R.id.guide_ll_point);
        //根据ViewPager的item数量实例化数组
        ivPointArray = new ImageView[viewList.size()];
        //循环新建底部圆点ImageView，将生成的ImageView保存到数组中
        int size = viewList.size();
        for (int i = 0; i < size; i++) {
            iv_point = new ImageView(this);
            iv_point.setLayoutParams(new ViewGroup.LayoutParams(20, 20));
            iv_point.setPadding(30, 0, 30, 0);//left,top,right,bottom
            ivPointArray[i] = iv_point;
            //第一个页面需要设置为选中状态，这里采用两张不同的图片
            if (i == 0) {
                iv_point.setBackgroundResource(R.mipmap.dot_selected);
            } else {
                iv_point.setBackgroundResource(R.mipmap.dot_normal);
            }
            //将数组中的ImageView加入到ViewGroup
            vg.addView(ivPointArray[i]);
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        TextView.startAutoScroll();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        //修改全部的position长度
        int newPosition = position % viewList.size();

        //循环设置当前页的标记图
        int length = ivPointArray.length;
        for (int i = 0;i<length;i++){
            ivPointArray[newPosition].setBackgroundResource(R.mipmap.dot_selected);
            if (newPosition != i){
                ivPointArray[i].setBackgroundResource(R.mipmap.dot_normal);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.level1:
                Intent intent=new Intent(mActivity,SecondActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_jump:
                Intent intent1=new Intent(mActivity,WebActivity.class);
                startActivity(intent1);
                break;
            case R.id.btn_jump2:
                Toast.makeText(mActivity,"点点",Toast.LENGTH_SHORT).show();
                new Thread(new Runnable() {
                @Override
                 public void run() {
                for (int i=10;i>0;i--){
                  Message msg=new Message();
                     msg.what=0x123;//消息处理的标识
                     msg.arg1=i;//传入值
                     msg.obj="和战恒";//传入对象
                     handler.sendMessage(msg);//发送消息
          try {
              Thread.sleep(1000);
          } catch (InterruptedException e) {
              e.printStackTrace();
          }
      }
    }
}).start();
                break;
            case R.id.btn_jump3:
                new MyTask().execute();//自动调用doinBackground,不用手动调用
                break;
            case R.id.btn_jump4:
                Intent intent2=new Intent(mActivity,TabActivity.class);
                startActivity(intent2);
                break;
            case R.id.btn_jump5:
                Intent intent3=new Intent(mActivity,GalleryActivity.class);
                startActivity(intent3);
                break;
        }
    }
    /*
    * params执行子线程需要传入的参数类型
    *progress进度指示所需的类型
    * result运行后的结果类型
    * */
    class MyTask extends AsyncTask<Void,Integer,Void>{

        @Override
//        运行在后台（子线程），相当于thread的run方法
        protected Void doInBackground(Void... params) {
            for (int i=0;i<100;i++){
                publishProgress(i);//自动调用onProgressUpdates
                try {
                    Thread.sleep(1000);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            return null;
        }
        /*
        * 进度指示的方法
        * 运行在主线程中，在这个方法中可直接改变控件的属性
        * */
        protected void onProgressUpdate(Integer...values){
             int num=values[0];
            tv_handler.setText("num:"+num);//更新主线程中的UI属性
            super.onProgressUpdate(values);
        }
        /*
        * 运行在主线程中，当后台运行结束后，自动调用的方法
        *
        * */
        protected void onPostExecute(Void result){
            Toast.makeText(MainActivity.this,"子线程运行结束",Toast.LENGTH_SHORT).show();
            super.onPostExecute(result);
        }
    }
}
