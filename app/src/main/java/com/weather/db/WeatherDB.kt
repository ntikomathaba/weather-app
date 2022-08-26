package com.weather.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.weather.db.dao.LocalWeatherDao
import com.weather.db.entities.Weather


@Database(
    entities = [
        Weather::class
    ],
    version = 1,
    exportSchema = false
)
abstract class WeatherDB : RoomDatabase() {
    abstract fun localWeatherDao() : LocalWeatherDao
}