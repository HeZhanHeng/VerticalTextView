package com.example.zhkj.verticaltextview.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by HeZhanHeng on 2018/12/12.
 */

@SuppressLint("AppCompatCustomView")
public class MarqueTextView extends TextView {
    public MarqueTextView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    public MarqueTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public MarqueTextView(Context context) {
        super(context);
    }
    @Override
    public boolean isFocused() {
        //就是把这里返回true即可
        return true;
    }
}
