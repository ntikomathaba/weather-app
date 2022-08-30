package com.weather.repository.remote

import com.weather.api.WeatherRestApiService
import com.weather.domain.enums.MeasurementType
import com.weather.models.ForecastResponse
import com.weather.models.WeatherResponse
import com.weather.util.Resource
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
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
                     metric = MeasurementType.METRIC.measurement
                 )
             )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "Error has occurred")
        }
    }

    suspend fun getForecast(lat: Double, long: Double): ForecastResponse{
        return weatherRestApiService.getWeatherForecast(appid = apiKey, lat = lat, long = long, metric = MeasurementType.METRIC.measurement)
    }

    suspend fun getWeatherForecast(lat: Double, long: Double): Resource<ForecastResponse>{
        return try {
            Resource.Success(
                data = weatherRestApiService.getWeatherForecast(
                    appid = apiKey,
                    lat = lat,
                    long = long,
                    metric = MeasurementType.METRIC.measurement
                )
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "Error has occurred")
        }
    }
}