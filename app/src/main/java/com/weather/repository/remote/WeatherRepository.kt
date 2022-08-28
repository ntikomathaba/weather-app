package com.weather.repository.remote

import com.weather.api.WeatherRestApiService
import com.weather.domain.enum.MetricType
import com.weather.models.WeatherResponse
import com.weather.util.Resource
import javax.inject.Inject
import javax.inject.Named

class WeatherRepository @Inject constructor(
    private val weatherRestApiService: WeatherRestApiService,
    @Named("apiKey") private val apiKey: String,
    @Named("googleApiKey") private val googleApiKey: String,
){
    suspend fun getWeather(lat: Double, long: Double): Resource<WeatherResponse> {
        return try {
             Resource.Success(
                 data = weatherRestApiService.getWeather(
                     appid = apiKey,
                     lat = lat,
                     long = long,
                     metric = MetricType.CELSIUS.metric
                 )
             )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "Error has occurred")
        }
    }
}