package com.jingdong.sdk.generalandroidframework.ui;

import android.os.Bundle;

import com.jingdong.sdk.generalandroidframework.BaseApplication;
import com.jingdong.sdk.generalandroidframework.R;
import com.jingdong.sdk.generalandroidframework.ui.base.BaseActivity;

public class GreenDaoActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green_dao);

//        DaoSession daoSession = ((BaseApplication) getApplication()).getDaoSession();
//        noteDao = daoSession.getNoteDao();
    }
}
