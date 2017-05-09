package com.jingdong.sdk.generalandroidframework;

import android.app.Application;

/**
 * Created by quzhiyong on 2017/5/9.
 */

public class BaseApplication extends Application {

    /** A flag to show how easily you can switch from standard SQLite to the encrypted SQLCipher. */
    public static final boolean ENCRYPTED = true;

//    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();

    }
}
