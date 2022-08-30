package com.weather.repository.local

import com.weather.db.dao.LocalWeatherDao
import com.weather.db.entities.Coordinates
import com.weather.db.entities.Weather
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalWeatherRepository @Inject constructor(
    private val localWeatherDao: LocalWeatherDao
) {

    suspend fun insertWeather(weather: Weather){
        localWeatherDao.insertWeather(weather)
    }

    suspend fun insertCoordinates(coordinates: Coordinates){
        localWeatherDao.insertCoordinates(coordinates)
    }

    fun getWeatherLatestWeather() {
        localWeatherDao.latestWeatherUpdate
    }
}