package com.jingdong.sdk.generalandroidframework.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.jingdong.sdk.generalandroidframework.R;
import com.jingdong.sdk.generalandroidframework.http.service.GithubService;
import com.jingdong.sdk.generalandroidframework.http.bean.GithubUserBean;

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
