package com.example.zhkj.verticaltextview.utils;

import android.content.Context;

/**
 * Created by Administrator on 2020/5/7 0007.
 */

public class DisplayUtils {
    public static int dp2px(Context context, float dpValue)
    {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

}
