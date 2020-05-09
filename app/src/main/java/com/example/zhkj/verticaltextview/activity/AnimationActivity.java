package com.example.zhkj.verticaltextview.activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.zhkj.verticaltextview.R;

/*
* @description:逐帧动画、补间动画、属性动画
* 补间动画：只是改变view的显示效果，不会改变view的属性，功能比较单调：四种，针对的对象只是UI控件
*属性动画：执行的动画不只是ui控件，可以对任何对象执行动画（不管是否显示在屏幕上）
*
* */
public class AnimationActivity extends AppCompatActivity implements View.OnClickListener {
    private final String TAG = getClass().getSimpleName();
    private Button btn_translate, btn_scale, btn_rotate, btn_alpha,btn_group;
    private ImageView img_example;
    private int height = 800;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        initView();
        initAnim();
    }

    private void initView() {
        btn_translate = findViewById(R.id.btn_translate);
        btn_translate.setOnClickListener(this);
        btn_scale = findViewById(R.id.btn_scale);
        btn_scale.setOnClickListener(this);
        btn_rotate = findViewById(R.id.btn_rotate);
        btn_rotate.setOnClickListener(this);
        btn_alpha = findViewById(R.id.btn_alpha);
        btn_alpha.setOnClickListener(this);
        btn_group=findViewById(R.id.btn_group);
        btn_group.setOnClickListener(this);
        img_example = findViewById(R.id.img_example);
        img_example.setOnClickListener(this);
    }

    private ObjectAnimator animator1;
    private ObjectAnimator animator2;
    private ObjectAnimator animator3;
    private ObjectAnimator animator4;

    private void initAnim() {
//        animator1 = ObjectAnimator.ofFloat(img_example, "translationY", height / 8, -100, height / 2);
        animator1 = ObjectAnimator.ofFloat(img_example, "translationY", 0f, -100, 0f);
        animator2 = ObjectAnimator.ofFloat(img_example, "scaleX", 2f, 4f, 1f, 0.5f, 1f);
        animator3 = ObjectAnimator.ofFloat(img_example, "rotation", 0f, 360f, 0f);
        animator4 = ObjectAnimator.ofFloat(img_example, "alpha", 1f, 0f, 1f, 0f, 1f);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_translate:
//                Animation animation = AnimationUtils.loadAnimation(AnimationActivity.this, R.anim.translate);
//                animation.setFillAfter(true);//结束后不恢复原位
//                img_example.startAnimation(animation);
                animator1.setDuration(3000L);
                animator1.start();
                break;
            case R.id.btn_scale:
//                Animation animation1 = AnimationUtils.loadAnimation(AnimationActivity.this, R.anim.scale);
//                img_example.startAnimation(animation1);
                animator2.setDuration(3000L);
                animator2.start();
                break;
            case R.id.btn_rotate:
//                Animation animation2 = AnimationUtils.loadAnimation(AnimationActivity.this, R.anim.rotate);
//                img_example.startAnimation(animation2);
                animator3.setDuration(3000L);
                animator3.start();
                break;
            case R.id.btn_alpha:
//                Animation animation3 = AnimationUtils.loadAnimation(AnimationActivity.this, R.anim.alpha);
//                img_example.startAnimation(animation3);
//                animation3.setFillAfter(true);
                animator4.setDuration(3000L);
                animator4.start();
                break;
            case R.id.btn_group:
                AnimatorSet animationSet=new AnimatorSet();
                animationSet.play(animator4).with(animator3).with(animator2).after(animator1);
                animationSet.setDuration(5000l);
                animationSet.start();
                break;
            case R.id.img_example:
                Toast.makeText(AnimationActivity.this, "猜猜我在哪里？", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
