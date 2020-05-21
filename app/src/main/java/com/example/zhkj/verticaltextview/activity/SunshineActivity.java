package com.example.zhkj.verticaltextview.activity;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zhkj.verticaltextview.R;
import com.example.zhkj.verticaltextview.view.CircleProgressView;
import com.example.zhkj.verticaltextview.view.CustomPopWindow;
import com.example.zhkj.verticaltextview.view.SunAnimationView;
import com.example.zhkj.verticaltextview.view.TipsDialog;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class SunshineActivity extends AppCompatActivity {
    Button button, btn_pop, btn_location;
    private TextView tv_marquee;
    SunAnimationView sumView;
    private String mCurrentTime;
    private CircleProgressView circleView;
    private static final int BAIDU_READ_PHONE_STATE = 100;//定位权限请求
    private static final int PRIVATE_CODE = 1315;//开启GPS权限
    private LocationManager lm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sunshine);
        initView();
        showGPSContacts();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initView();
    }

    private void initView() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        mCurrentTime = simpleDateFormat.format(date);
        sumView = (SunAnimationView) findViewById(R.id.sun_view);
        sumView.setTimes("05:29", "19:16", mCurrentTime);
        button = findViewById(R.id.btn_set_time);
        button.setText("当前时间：" + mCurrentTime);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TipsDialog tipsDialog = new TipsDialog(SunshineActivity.this, R.style.dialog, "弹窗内容在这里显示", false);
                tipsDialog.setNegative("取消");
                tipsDialog.setPositive("我知道了");
                tipsDialog.show();
                tipsDialog.setListener(new TipsDialog.OnCloseListener() {
                    @Override
                    public void onClick(Dialog dialog, boolean confirm, String etContent) {
                        if (confirm) {
                            Toast.makeText(SunshineActivity.this, "确认" + etContent, Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(SunshineActivity.this, "取消" + etContent, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        btn_pop = findViewById(R.id.btn_pop);
        btn_pop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CustomPopWindow customPopWindow = new CustomPopWindow(SunshineActivity.this);
                customPopWindow.showAtLocation(v,
                        Gravity.CENTER, 0, 0);
                customPopWindow.setTitle("hello 123");
                customPopWindow.isShowing();
//                可以在自定义pop里暴露一个接口给外部使用，也可在pop内部进行操作
//                customPopWindow.setOnItemClickListener(new CustomPopWindow.OnItemClickListener() {
//                    @Override
//                    public void click(View popupWindow, boolean confirm) {
//                        if (confirm){
//                            customPopWindow.dismiss();
//                            customPopWindow.backgroundAlpha(SunshineActivity.this,1f);
//                        }
//                    }
//                });
            }
        });
        btn_location = findViewById(R.id.btn_location);
        btn_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("点击事件=", "触发了");
//                initLocation();
            }
        });
        //        横向滚动的广告
        tv_marquee = (TextView) findViewById(R.id.tv_marquee);
        tv_marquee.setText("此段代码由河南省郑州市管城区国际陆港开发建设有限公司之中浩科技有限公司综合业务部一分部和战恒编写");
        tv_marquee.setSelected(true);
        circleView = (CircleProgressView) findViewById(R.id.circleView);
        circleView.setMax(100);
        int progress = 50;
        String text = "当前温度" + progress + "C";
        circleView.setProgressAndText(progress, text);
    }

    /**
     * 检测GPS、位置权限是否开启
     */
    public void showGPSContacts() {
        lm = (LocationManager) this.getSystemService(this.LOCATION_SERVICE);
        boolean ok = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (ok) {//开了定位服务
            if (Build.VERSION.SDK_INT >= 23) { //判断是否为android6.0系统版本，如果是，需要动态添加权限
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                        != PERMISSION_GRANTED) {// 没有权限，申请权限。
                    ActivityCompat.requestPermissions(this, LOCATIONGPS,
                            BAIDU_READ_PHONE_STATE);
                } else {
                    getLocation();//getLocation为定位方法
                }
            } else {
                getLocation();//getLocation为定位方法
            }
        } else {
            Toast.makeText(this, "系统检测到未开启GPS定位服务,请开启", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.setAction(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivityForResult(intent, PRIVATE_CODE);
        }
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    /**
     * 获取具体位置的经纬度
     */
    private void getLocation() {
        // 获取位置管理服务
        LocationManager locationManager;
        String serviceName = Context.LOCATION_SERVICE;
        locationManager = (LocationManager) this.getSystemService(serviceName);
        // 查找到服务信息
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE); // 高精度
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        criteria.setCostAllowed(true);
        criteria.setPowerRequirement(Criteria.POWER_HIGH); // 低功耗
//        String provider = locationManager.getBestProvider(criteria, true); // 获取GPS信息
        /**这段代码不需要深究，是locationManager.getLastKnownLocation(provider)自动生成的，不加会出错**/
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER); // 通过GPS获取位置
        updateLocation(location);
    }

    /**
     * 获取到当前位置的经纬度
     *
     * @param location
     */
    private void updateLocation(Location location) {
        if (location != null) {
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();
            Log.e("定位结果=", "维度：" + latitude + "\n经度" + longitude);
            Geocoder geocoder=new Geocoder(this);
            List<Address> addresses = null;
            try {
               addresses= geocoder.getFromLocation(latitude,longitude,10);
            } catch (IOException e) {
                e.printStackTrace();
            }
              String city=addresses.get(0).getAdminArea();
            String province=addresses.get(0).getLocality();
            Log.e("定位城市结果=", city+province);
        } else {
            Log.e("定位结果=", "无法获取到位置信息");
        }
    }

    /**
     * Android6.0申请权限的回调方法
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            // requestCode即所声明的权限获取码，在checkSelfPermission时传入
            case BAIDU_READ_PHONE_STATE:
                //如果用户取消，permissions可能为null.
                if (grantResults[0] == PERMISSION_GRANTED && grantResults.length > 0) {  //有权限
                    // 获取到权限，作相应处理
                    getLocation();
                } else {
                    showGPSContacts();
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case PRIVATE_CODE:
                showGPSContacts();
                break;

        }
    }

    static final String[] LOCATIONGPS = new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.READ_PHONE_STATE};
}
