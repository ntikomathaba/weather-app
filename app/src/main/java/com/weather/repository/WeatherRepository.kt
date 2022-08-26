package com.weather.repository

import com.weather.api.WeatherRestApiService
import com.weather.models.WeatherResponse
import javax.inject.Inject
import javax.inject.Named

class WeatherRepository @Inject constructor(
    private val weatherRestApiService: WeatherRestApiService,
    @Named("apiKey") private val apiKey: String
){
    suspend fun getWeather(lat: Double, long: Double): WeatherResponse {
        return weatherRestApiService.getWeather(
            appid = apiKey,
            lat = lat,
            long = long
        )
    }
}