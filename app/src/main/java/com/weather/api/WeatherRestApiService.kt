package com.weather.api

import com.weather.models.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherRestApiService {

    @GET(value = "/data/2.5/weather")
    suspend fun getWeather(
        @Query("APPID") appid: String,
        @Query("lat") lat: Double,
        @Query("lon") long: Double
    ): WeatherResponse
}