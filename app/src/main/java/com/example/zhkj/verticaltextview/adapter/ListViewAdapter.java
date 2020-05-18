package com.example.zhkj.verticaltextview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.zhkj.verticaltextview.R;

import java.util.List;

/**
 * Created by Administrator on 2020/5/15 0015.
 */

public class ListViewAdapter extends BaseAdapter {
    private List<String> list;
    private Context context;

    public void setOnTvClickListener(OnTvClickListener onTvClickListener) {
        this.onTvClickListener = onTvClickListener;
    }

    private OnTvClickListener onTvClickListener;
    public interface OnTvClickListener  {
        void onClick(int pos);
    }
    public ListViewAdapter(Context context, List<String> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_test, parent, false);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv_item_desc=convertView.findViewById(R.id.tv_item_desc);
        holder.tv_item_desc.setText(list.get(position).toString());
        holder.tv_item_desc.setTag(position);
        holder.tv_item_desc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onTvClickListener.onClick((Integer) v.getTag());
            }
        });
        return convertView;
    }

    public class ViewHolder {
        TextView tv_item_desc;
    }
}
