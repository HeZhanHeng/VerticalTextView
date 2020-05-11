package com.example.zhkj.verticaltextview.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.zhkj.verticaltextview.R;

/**
 * Created by Administrator on 2020/5/11 0011.
 * @description:自定义dialog
 */

public class TipsDialog extends Dialog implements View.OnClickListener {
    private TextView contentTxt;
    private EditText etContentTxt;
    private TextView titleTxt;
    private TextView submitTxt;
    private TextView cancelTxt;
    private Context mContext;
    private String content;//文本
    private OnCloseListener listener;
    private String positiveName;
    private String negativeName;
    private String title;//标题
    private boolean isSingle;//展示单个按钮
    private String etContent;//可编辑文本

    //继承：第一个方法必须的
    public TipsDialog(@NonNull Context context) {
        super(context);
        this.mContext = context;
    }
//    可以提供给外部使用的诸多方法
    public TipsDialog(@NonNull Context context, int themeResId, String content) {
        super(context, themeResId);
        this.mContext = context;
        this.content = content;
    }

    public TipsDialog(@NonNull Context context, int themeResId, String content, boolean isSingle) {
        super(context, themeResId);
        this.mContext = context;
        this.content = content;
        this.isSingle = isSingle;
    }

    public TipsDialog(@NonNull Context context, int themeResId, String content, OnCloseListener listener) {
        super(context, themeResId);
        this.mContext = context;
        this.content = content;
        this.listener = listener;
    }

    protected TipsDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.mContext = context;
    }
//提供给外部设置标题文本等内容
    public TipsDialog setTitle(String title) {
        this.title = title;
        return this;
    }
    public TipsDialog setPositive(String positiveName) {
        this.positiveName = positiveName;
        return this;
    }
    public TipsDialog setNegative(String negativeName) {
        this.negativeName = negativeName;
        return this;
    }
    //    提供一个接口供外部使用
    public void setListener(OnCloseListener listener) {
        this.listener = listener;
    }
    public interface OnCloseListener {
        void onClick(Dialog dialog, boolean confirm,String etContent);
    }
//    开始
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_tips);
        setCanceledOnTouchOutside(true);//点击外部是否消失
        initView();
    }
    private void initView(){
        contentTxt = findViewById(R.id.content);
        titleTxt = findViewById(R.id.title);
        submitTxt = findViewById(R.id.submit);
        submitTxt.setOnClickListener(this);
        cancelTxt = findViewById(R.id.cancel);
        cancelTxt.setOnClickListener(this);
        contentTxt.setText(content);
        etContentTxt=findViewById(R.id.et_content);
        etContentTxt.setHint("请输入文本");
        etContentTxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                etContent=etContentTxt.getText().toString().trim();
            }
        });

        if(!TextUtils.isEmpty(positiveName)){
            submitTxt.setText(positiveName);
        }
        if (isSingle) {
            cancelTxt.setVisibility(View.GONE);
            findViewById(R.id.line).setVisibility(View.GONE);
        } else {
            if (!TextUtils.isEmpty(negativeName)) {
                cancelTxt.setText(negativeName);
            }
        }
        if(!TextUtils.isEmpty(title)){
            titleTxt.setVisibility(View.VISIBLE);
            titleTxt.setText(title);
        }else {
            titleTxt.setVisibility(View.GONE);
        }

    }
    @Override
    public void onClick(View v) {
       switch (v.getId()){
           case R.id.cancel:
               if (listener!=null){
                   listener.onClick(this,false,etContent);
               }
               this.dismiss();
               break;
           case R.id.submit:
               if (listener!=null){
                   listener.onClick(this,true,etContent);
               }
               this.dismiss();
               break;
       }
    }

}
