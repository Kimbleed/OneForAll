package com.example.oneforall.activity;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitAndRxJavaActivity extends BaseActivity{
    @Override
    int getContentId() {
        return 0;
    }

    @Override
    void initView() {

    }

    @Override
    void initData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://baidu.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();
    }
}
