package com.example.zhkj.verticaltextview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Rational;
import android.view.View;

/**
 * Created by Administrator on 2020/5/7 0007.
 */

public class SelfView extends View {
    public SelfView(Context context) {
        super(context);
    }

    public SelfView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SelfView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint=new Paint();
        paint.setAntiAlias(true);//抗锯齿功能
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(0xFFA4C739);
        RectF rectF=new RectF(10,10,500,500);//确定外切矩形的范围
        rectF.offset(100,20);//偏移量
        canvas.drawArc(rectF,0,360,false,paint);// 开始角度（以时钟3点的方向为0°，逆时针为正方向）扫过角度（以时钟3点的方向为0°，逆时针为正方向）
    }
}
