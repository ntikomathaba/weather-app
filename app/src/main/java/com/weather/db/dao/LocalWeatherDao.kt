package com.weather.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.weather.models.WeatherResponse

@Dao
interface LocalWeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeather(weatherResponse: WeatherResponse)
}