package com.jingdong.sdk.generalandroidframework.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Index;

/**
 * Created by quzhiyong on 2017/5/11.
 */
@Entity
public class User {
    @Id(autoincrement = true)
    private Long id;        // greenDAO中的主键强制成Long类型
    @Index(unique = true)
    private String name;    // 值得注意的是，greenDAO只支持一对一，一对多，不支持多对多的映射关系
    private int age;
    @Generated(hash = 1309193360)
    public User(Long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
    @Generated(hash = 586692638)
    public User() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return this.age;
    }
    public void setAge(int age) {
        this.age = age;
    }
}
