package com.jingdong.sdk.generalandroidframework.http;

import com.jingdong.sdk.generalandroidframework.response.BannerResponse;
import com.jingdong.sdk.generalandroidframework.response.BookSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by quzhiyong on 2017/5/13.
 */

public interface BlueService {
    @GET("Public/banners")
    Call<BannerResponse> banners();
}
