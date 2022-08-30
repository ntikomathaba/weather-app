package com.weather.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.weather.db.entities.Coordinates
import com.weather.db.entities.Weather

@Dao
interface LocalWeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeather(weatherResponse: Weather)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCoordinates(location: Coordinates): Long

    @get:Query("SELECT * FROM weather ORDER BY id DESC LIMIT 1")
    val latestWeatherUpdate: Weather
}