package com.jingdong.sdk.generalandroidframework.bean;

import java.util.List;

/**
 * Created by quzhiyong on 2017/5/13.
 */

public class BannerBean {

    private int total;
    private List<BannerListBean> list;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<BannerListBean> getList() {
        return list;
    }

    public void setList(List<BannerListBean> list) {
        this.list = list;
    }
}
