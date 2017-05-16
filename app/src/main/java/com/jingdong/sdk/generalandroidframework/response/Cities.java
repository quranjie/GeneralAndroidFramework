package com.jingdong.sdk.generalandroidframework.response;

import java.util.List;

/**
 * Created by quzhiyong on 2017/5/13.
 */

public class Cities {
    private String title;
    private List<City> list;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<City> getList() {
        return list;
    }

    public void setList(List<City> list) {
        this.list = list;
    }
}
