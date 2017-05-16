package com.jingdong.sdk.generalandroidframework.ui;

import android.content.Intent;
import android.os.Bundle;

import com.jingdong.sdk.generalandroidframework.R;
import com.jingdong.sdk.generalandroidframework.ui.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent();
        intent.setClass(this, RetrofitActivity.class);
        startActivity(intent);
    }
}
