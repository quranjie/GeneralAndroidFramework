package com.jingdong.sdk.generalandroidframework.response;

/**
 * Created by quzhiyong on 2017/5/13.
 */

public class BaseResponse {

    protected int rspCode;
    protected String rspMsg;

    public int getRspCode() {
        return rspCode;
    }

    public void setRspCode(int rspCode) {
        this.rspCode = rspCode;
    }

    public String getRspMsg() {
        return rspMsg;
    }

    public void setRspMsg(String rspMsg) {
        this.rspMsg = rspMsg;
    }
}
