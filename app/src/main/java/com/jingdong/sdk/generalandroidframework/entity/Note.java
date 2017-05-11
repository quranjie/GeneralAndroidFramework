package com.jingdong.sdk.generalandroidframework.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by quzhiyong on 2017/5/11.
 */
@Entity
public class Note {
    @Id(autoincrement = true)
    private Long id;        // greenDAO中的主键强制成Long类型
    @Index(unique = true)
    private String serial_no;
    @Generated(hash = 1777850059)
    public Note(Long id, String serial_no) {
        this.id = id;
        this.serial_no = serial_no;
    }
    @Generated(hash = 1272611929)
    public Note() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getSerial_no() {
        return this.serial_no;
    }
    public void setSerial_no(String serial_no) {
        this.serial_no = serial_no;
    }
}
