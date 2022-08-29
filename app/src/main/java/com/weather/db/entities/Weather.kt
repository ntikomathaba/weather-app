package com.weather.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fasterxml.jackson.annotation.JsonProperty
import com.weather.models.*

@Entity(tableName = "weather")
data class Weather(

    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,

    val location: String,
    val temp: Double? = null,

    @JsonProperty("temp_min")
    val tempMin: Double? = null,
    val humidity: Int? = null,
    val pressure: Int? = null,
    val feelsLike: Double? = null,

    @JsonProperty("temp_max")
    val tempMax: Double? = null
)