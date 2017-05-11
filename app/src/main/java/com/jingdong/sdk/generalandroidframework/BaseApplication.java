package com.jingdong.sdk.generalandroidframework;

import android.app.Application;
import android.content.Context;

import com.jingdong.sdk.generalandroidframework.greendao.DaoMaster;
import com.jingdong.sdk.generalandroidframework.greendao.DaoSession;
import com.jingdong.sdk.generalandroidframework.greendao.MySQLiteOpenHelper;

import org.greenrobot.greendao.database.Database;

/**
 * Created by quzhiyong on 2017/5/9.
 */

public class BaseApplication extends Application {

    private static final String DATA_BASE_NAME = "general.db";

    private static DaoSession daoSession;

    private static BaseApplication baseApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        baseApplication = this;
        setupDataBase(getApplicationContext());
    }

    private void setupDataBase(Context context) {
        MySQLiteOpenHelper openHelper = new MySQLiteOpenHelper(context, DATA_BASE_NAME, null);
        Database db = openHelper.getEncryptedWritableDb("<your-secret-password>");
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    public static BaseApplication getInstance() {
        return baseApplication;
    }

    public static DaoSession getDaoSession() {
        return daoSession;
    }
}
