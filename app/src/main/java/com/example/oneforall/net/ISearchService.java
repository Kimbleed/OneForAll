package com.example.oneforall.net;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ISearchService {
    @GET("s")
    Call<ResponseBody> search(@Query("wd")String input);
}
