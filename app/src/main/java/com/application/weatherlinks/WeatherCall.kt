package com.application.weatherlinks

import com.application.weatherlinks.models.model
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherCall {

    @GET("/data/2.5/weather?")
    suspend fun getWeather(
        @Query("q")
        cityName: String,
        @Query("appid")
        apiKey: String,
    ) : Response<model>

}