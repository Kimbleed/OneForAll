package com.example.oneforall.activity;

import com.example.oneforall.utils.Logger;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkhttpActivity extends BaseActivity{
    @Override
    int getContentId() {
        return 0;
    }

    @Override
    void initView() {

    }

    @Override
    void initData() {
        String url = "https://baidu.com/s?wd=123";
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(5000, TimeUnit.MILLISECONDS) //连接超时
                .readTimeout(5000, TimeUnit.MILLISECONDS) //读取超时
                .writeTimeout(5000, TimeUnit.MILLISECONDS) //写入超时
                .build();
        Request request =new Request.Builder().url(url).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Logger.getInstance().e(TAG,e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Logger.getInstance().i(TAG,response.body().string());
            }
        });

    }
}
