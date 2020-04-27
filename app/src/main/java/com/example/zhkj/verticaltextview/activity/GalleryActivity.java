package com.example.zhkj.verticaltextview.activity;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.zhkj.verticaltextview.R;
import com.example.zhkj.verticaltextview.utils.ImageUtil;

import java.util.Timer;
import java.util.TimerTask;

public class GalleryActivity extends AppCompatActivity {
    /**
     * 图片资源数组
     */
    private int[] imageResIDs;
    private MyGallery gallery;
    private int index = 0;
    private final int AUTOPLAY = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        imageResIDs = new int[]{R.drawable.nba1, R.drawable.nba3, R.drawable.nba1, R.drawable.nba3,
                R.drawable.nba1};

        gallery = (MyGallery) findViewById(R.id.mygallery);
        ImageAdapter adapter = new ImageAdapter();
        gallery.setAdapter(adapter);
        gallery.setSpacing(50); //图片之间的间距
        gallery.setSelection((Integer.MAX_VALUE / 2) - (Integer.MAX_VALUE / 2) % imageResIDs.length);

        gallery.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        // 设置点击事件监听
        gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(GalleryActivity.this, "当前位置position:"+position+"的图片被选中了", Toast.LENGTH_SHORT).show();
            }
        });

//        Timer timer = new Timer();
//        timer.schedule(task, 3000, 3000);

    }

    /**
     * 定时器，实现自动播放
     */
    private TimerTask task = new TimerTask() {
        @Override
        public void run() {
            Message message = new Message();
            message.what = AUTOPLAY;
            index = gallery.getSelectedItemPosition();
            index++;
            handler.sendMessage(message);
        }
    };

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case AUTOPLAY:
                    gallery.setSelection(index);
                    break;
                default:
                    break;
            }
        }
    };

    public class ImageAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;//用于循环滚动
        }

        @Override
        public Object getItem(int position) {
            if (position >= imageResIDs.length) {
                position = position % imageResIDs.length;
            }

            return position;
        }

        @Override
        public long getItemId(int position) {
            if (position >= imageResIDs.length) {
                position = position % imageResIDs.length;
            }

            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ImageView imageView;
            if (convertView != null) {
                imageView = (ImageView) convertView;
            } else {
                imageView = new ImageView(GalleryActivity.this);
            }

            if (position >= imageResIDs.length) {
                position = position % imageResIDs.length;
            }

            Bitmap bitmap = ImageUtil.getImageBitmap(getResources(),
                    imageResIDs[position]);
            BitmapDrawable drawable = new BitmapDrawable(bitmap);
            drawable.setAntiAlias(true); // 消除锯齿
            imageView.setImageDrawable(drawable);
            Gallery.LayoutParams params = new Gallery.LayoutParams(240, 320);
            imageView.setLayoutParams(params);
            return imageView;
        }
    }

}
