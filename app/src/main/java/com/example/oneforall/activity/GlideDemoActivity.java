package com.example.oneforall.activity;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.oneforall.R;

public class GlideDemoActivity extends BaseActivity{

    ImageView imgView;
    String imgUrl = "https://image.gcores.com/666c96b0-817e-4813-829f-3aeb1a373b5d.png?x-oss-process=image/quality,q_90/format,webp";
    @Override
    int getContentId() {
        return R.layout.activity_img;
    }

    @Override
    void initView() {
        imgView = findViewById(R.id.img);

    }

    @Override
    void initData() {
        Glide.with(this.CTX).load(imgUrl).into(imgView);
    }
}
