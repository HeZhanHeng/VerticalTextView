package com.example.zhkj.verticaltextview.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.zhkj.verticaltextview.R;

/**
 * Created by Administrator on 2020/5/11 0011.
 */

public class CustomPopWindow extends PopupWindow {
    public static final String TAG = "popWindow";
    private View view;
    private Activity context;
    private String popTitle, content;
    private OnItemClickListener onItemClickListener;
    private TextView title;
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
    public CustomPopWindow setTitle(String popTitle) {
        this.popTitle = popTitle;
        title.setText(popTitle);
        return this;
    }

    public CustomPopWindow setContent(String content) {
        this.content = content;
        return this;
    }

    public CustomPopWindow(Activity context) {
        super(context);
        this.context = context;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.dialog_tips, null, false);
        initView();
        initPopWindow();
    }

    private void initView() {
        TextView cancel = view.findViewById(R.id.cancel);
        TextView submit = view.findViewById(R.id.submit);
        title=view.findViewById(R.id.title);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (onItemClickListener!=null){
//                    onItemClickListener.click(view,true);
//                }
//                在内部对弹窗进行操作也可以
                dismiss();
                backgroundAlpha(context,1f);
            }
        });
        this.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(context,1f);
            }
        });
    }

    private void initPopWindow() {
         this.setContentView(view);
         this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setTouchable(true);
//        下面这两句可以控制点击外部是否取消
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x00FFFFFF);
        //设置弹出窗体的背景
        this.setBackgroundDrawable(dw);
        backgroundAlpha(context,0.5f);
    }


    /**
     * 设置添加屏幕的背景透明度(值越大,透明度越高)
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(Activity context, float bgAlpha) {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        context.getWindow().setAttributes(lp);
    }
    public interface OnItemClickListener {
        void click(View popupWindow, boolean confirm);
    }
}
