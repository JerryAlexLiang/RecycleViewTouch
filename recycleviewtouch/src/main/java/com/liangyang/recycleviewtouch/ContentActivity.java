package com.liangyang.recycleviewtouch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ContentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("data");
        String name = bundle.getString("name");
        String time = bundle.getString("time");
        String message = bundle.getString("message");
        int image_id = bundle.getInt("image_id");

        //初始化视图
        ImageView mImageView = (ImageView) findViewById(R.id.content_iv);
        TextView mName = (TextView) findViewById(R.id.content_name);
        TextView mTime = (TextView) findViewById(R.id.content_time);
        TextView mMessage = (TextView) findViewById(R.id.content_message);

        //数据映射
        mImageView.setImageResource(image_id);
        mName.setText(name);
        mTime.setText(time);
        mMessage.setText(message);

    }
}
