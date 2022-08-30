package com.weather.api

import com.weather.models.FavouriteLocation
import com.weather.models.ForecastResponse
import com.weather.models.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherRestApiService {

    @GET(value = "/data/2.5/weather")
    suspend fun getWeather(
        @Query("APPID") appid: String,
        @Query("lat") lat: Double,
        @Query("lon") long: Double,
        @Query("units") metric: String
    ): WeatherResponse

    @GET(value = "/data/2.5/forecast")
    suspend fun getWeatherForecast(
        @Query("APPID") appid: String,
        @Query("lat") lat: Double,
        @Query("lon") long: Double,
        @Query("units") metric: String
    ): ForecastResponse

    @GET(value = "/geo/1.0/direct")
    suspend fun searchFavouriteLocation(
        @Query("APPID") appid: String,
        @Query("q") q: String,
        @Query("limit") limit: Int
    ) : List<FavouriteLocation>

    @GET(value = "/data/2.5/weather")
    suspend fun getWeatherByCity(
        @Query("APPID") appid: String,
        @Query("q") cityName: String
    ): WeatherResponse

    @GET(value = "/data/2.5/forecast")
    suspend fun getForecastByCity(
        @Query("APPID") appid: String,
        @Query("q") cityName: String
    )


}