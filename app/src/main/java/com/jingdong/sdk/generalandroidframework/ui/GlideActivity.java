package com.jingdong.sdk.generalandroidframework.ui;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jingdong.sdk.generalandroidframework.R;

import java.io.File;

public class GlideActivity extends AppCompatActivity {

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide);

        imageView = (ImageView) findViewById(R.id.image_view);

        loadImage(imageView);
    }

    public void loadImage(View view) {
        String url = "http://cn.bing.com/az/hprichbg/rb/Dongdaemun_ZH-CN10736487148_1920x1080.jpg";
        Glide.with(this)
                .load(url)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .centerCrop()
                .crossFade()
//                .override(100, 100)     // 指定图片大小
                .into(imageView);

        /*
        // 加载本地图片
        File file = new File(getExternalCacheDir() + "/image.jpg");
        Glide.with(this).load(file).into(imageView);

        // 加载应用资源
        int resource = R.drawable.image;
        Glide.with(this).load(resource).into(imageView);

        // 加载二进制流
        byte[] image = getImageBytes();
        Glide.with(this).load(image).into(imageView);

        // 加载Uri对象
        Uri imageUri = getImageUri();
        Glide.with(this).load(imageUri).into(imageView);
        */
    }
}
