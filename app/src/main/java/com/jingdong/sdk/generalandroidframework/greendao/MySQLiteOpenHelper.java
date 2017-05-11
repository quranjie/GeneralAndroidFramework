package com.jingdong.sdk.generalandroidframework.greendao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.github.yuweiguocn.library.greendao.MigrationHelper;

/**
 * Created by quzhiyong on 2017/5/11.
 */

public class MySQLiteOpenHelper extends DaoMaster.OpenHelper {

    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 这里说明一下，数据库不外乎两种情况：
        // 1、修改表结构 2、添加新表
        // 下面一行代码就可以处理数据库升级，不用再倒表，非常方便
        MigrationHelper.migrate(db, UserDao.class, NoteDao.class);
    }
}
