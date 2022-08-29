package com.weather.repository.local

import com.weather.db.dao.LocalWeatherDao
import com.weather.db.entities.Weather
import com.weather.models.WeatherResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalWeatherRepository @Inject constructor(
    private val localWeatherDao: LocalWeatherDao
) {

    suspend fun insertWeather(weather: Weather){
        localWeatherDao.insertWeather(weather)
    }
}