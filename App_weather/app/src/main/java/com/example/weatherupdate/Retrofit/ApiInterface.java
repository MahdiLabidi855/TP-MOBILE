package com.example.weatherupdate.Retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("weather?APPID=fea94985298d5ecb52969ca2c7f65330&units=metric")
    Call<Example> getWeatherData(@Query("q")String name);
}
