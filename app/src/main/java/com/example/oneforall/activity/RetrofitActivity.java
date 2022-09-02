package com.example.oneforall.activity;

import com.example.oneforall.R;
import com.example.oneforall.net.ISearchService;
import com.example.oneforall.utils.Logger;
import com.google.gson.Gson;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitActivity extends BaseActivity{
    @Override
    int getContentId() {
        return R.layout.activity_default;
    }

    @Override
    void initView() {

    }

    @Override
    void initData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://baidu.com/")
//                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();

        ISearchService searchService =retrofit.create(ISearchService.class);
        Call<ResponseBody> call = searchService.search("123");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try{
                    Logger.getInstance().i(TAG,"response success:",response.isSuccessful());
                    Logger.getInstance().i(TAG,response.body().string());
//                    Logger.getInstance().i(TAG,response.raw().body().string());
                }
                catch (Exception e){
                    Logger.getInstance().e(TAG,e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Logger.getInstance().e(TAG,t.getMessage());
            }
        });

    }
}
