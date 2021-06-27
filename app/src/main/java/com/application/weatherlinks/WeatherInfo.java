package com.application.weatherlinks;

import com.application.weatherlinks.models.model;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherInfo {

    @GET("/data/2.5/weather?")
    Call<model> getInfo(
            @Query("q")
            String city,
            @Query("appid")
            String apiKey
    );

}
