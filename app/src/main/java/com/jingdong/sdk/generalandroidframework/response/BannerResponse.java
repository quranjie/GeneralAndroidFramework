package com.jingdong.sdk.generalandroidframework.response;

import com.jingdong.sdk.generalandroidframework.bean.BannerBean;

/**
 * Created by quzhiyong on 2017/5/13.
 */

public class BannerResponse extends BaseResponse{

    private BannerBean result;

    public BannerBean getResult() {
        return result;
    }

    public void setResult(BannerBean result) {
        this.result = result;
    }
}
