package com.jingdong.sdk.generalandroidframework.greendao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.jingdong.sdk.generalandroidframework.entity.User;
import com.jingdong.sdk.generalandroidframework.entity.Note;

import com.jingdong.sdk.generalandroidframework.greendao.UserDao;
import com.jingdong.sdk.generalandroidframework.greendao.NoteDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig userDaoConfig;
    private final DaoConfig noteDaoConfig;

    private final UserDao userDao;
    private final NoteDao noteDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        userDaoConfig = daoConfigMap.get(UserDao.class).clone();
        userDaoConfig.initIdentityScope(type);

        noteDaoConfig = daoConfigMap.get(NoteDao.class).clone();
        noteDaoConfig.initIdentityScope(type);

        userDao = new UserDao(userDaoConfig, this);
        noteDao = new NoteDao(noteDaoConfig, this);

        registerDao(User.class, userDao);
        registerDao(Note.class, noteDao);
    }
    
    public void clear() {
        userDaoConfig.clearIdentityScope();
        noteDaoConfig.clearIdentityScope();
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public NoteDao getNoteDao() {
        return noteDao;
    }

}
