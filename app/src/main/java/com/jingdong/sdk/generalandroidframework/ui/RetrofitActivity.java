package com.jingdong.sdk.generalandroidframework.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.jingdong.sdk.generalandroidframework.R;
import com.jingdong.sdk.generalandroidframework.http.BlueService;
import com.jingdong.sdk.generalandroidframework.http.GithubService;
import com.jingdong.sdk.generalandroidframework.http.GithubUserBean;
import com.jingdong.sdk.generalandroidframework.response.BannerResponse;
import com.jingdong.sdk.generalandroidframework.response.BookSearchResponse;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitActivity extends AppCompatActivity {

    private final String BASE_URL = "https://api.github.com/";
    private String name = "quranjie";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);

       /* Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://101.227.244.20/ysx/Rest/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        BlueService service = retrofit.create(BlueService.class);
        Call<BannerResponse> call = service.banners();

        call.enqueue(new Callback<BannerResponse>() {
            @Override
            public void onResponse(Call<BannerResponse> call, Response<BannerResponse> response) {
                BannerResponse res = response.body();
                int total = res.getResult().getTotal();
                Log.e("qu", "result=" + total);
//                asyncText.setText("异步请求结果: " + response.body().books.get(0).altTitle);
            }
            @Override
            public void onFailure(Call<BannerResponse> call, Throwable t) {
                Log.e("qu", "fail");
            }
        });*/

        LazyRetrofit();
    }

    private void LazyRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GithubService service = retrofit.create(GithubService.class);
        Call<GithubUserBean> call = service.getUser(name);
        call.enqueue(new Callback<GithubUserBean>() {
            @Override
            public void onResponse(Call<GithubUserBean> call, Response<GithubUserBean> response) {
                GithubUserBean bean = response.body();
//                setUserView(bean);
                Log.e("qu", bean.getAvatar_url());
            }

            @Override
            public void onFailure(Call<GithubUserBean> call, Throwable t) {
                Log.e("qu", "error");
            }
        });
    }
}
