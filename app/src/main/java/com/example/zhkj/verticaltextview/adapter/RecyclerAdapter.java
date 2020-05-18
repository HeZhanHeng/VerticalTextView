package com.example.zhkj.verticaltextview.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zhkj.verticaltextview.R;

import java.util.List;

/**
 * Created by Administrator on 2020/5/18 0018.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{
    private List<String> list;
    private Context context;
    private OnTextClickListener onTextClickListener;
    public void setOnTextClickListener(OnTextClickListener onTextClickListener) {
        this.onTextClickListener = onTextClickListener;
    }
    public interface OnTextClickListener{
        void click(int pos);
    }

    public RecyclerAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_test, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,final int i) {
        holder.tv_item_desc.setTag(i);
        holder.tv_item_desc.setText(list.get(i));
        holder.tv_item_desc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onTextClickListener.click(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_item_desc;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_item_desc=itemView.findViewById(R.id.tv_item_desc);
        }
    }
}
