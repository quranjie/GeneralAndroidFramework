package com.jingdong.sdk.generalandroidframework.ui;

import android.os.Bundle;
import android.util.Log;

import com.jingdong.sdk.generalandroidframework.BaseApplication;
import com.jingdong.sdk.generalandroidframework.R;
import com.jingdong.sdk.generalandroidframework.entity.User;
import com.jingdong.sdk.generalandroidframework.greendao.DaoSession;
import com.jingdong.sdk.generalandroidframework.greendao.UserDao;
import com.jingdong.sdk.generalandroidframework.ui.base.BaseActivity;

import java.util.List;

public class GreenDaoActivity extends BaseActivity {

    private UserDao userDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green_dao);

        DaoSession daoSession = ((BaseApplication) getApplication()).getDaoSession();
        userDao = daoSession.getUserDao();

        try {
            User user = new User();
            user.setId(1L);
            user.setName("test");
            user.setAge(28);
            userDao.insert(user);
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<User> users = userDao.loadAll();
        for (User u : users) {
            Log.i("greendao", "id=" + u.getId());
            Log.i("greendao", "name=" + u.getName());
            Log.i("greendao", "age=" + u.getAge());
        }
    }
}
