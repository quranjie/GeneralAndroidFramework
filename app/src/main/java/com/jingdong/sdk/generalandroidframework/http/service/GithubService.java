package com.jingdong.sdk.generalandroidframework.http.service;

import com.jingdong.sdk.generalandroidframework.http.bean.GithubUserBean;
import com.jingdong.sdk.generalandroidframework.http.bean.UserFollowerBean;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by quzhiyong on 2017/5/13.
 */

public interface GithubService {

    @GET("users/{user}")
    Call<ResponseBody> getUserString(@Path("user") String user);

    @GET("users/{user}")
    Call<GithubUserBean> getUser(@Path("user") String user);


    @GET("users/{user}/followers")
    Observable<List<UserFollowerBean>> followers(@Path("user") String usr);

}
