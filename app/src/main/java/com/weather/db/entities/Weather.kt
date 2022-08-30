package com.weather.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fasterxml.jackson.annotation.JsonProperty

@Entity(tableName = "weather")
data class Weather(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val temp: Double? = null,
    @JsonProperty("temp_min")
    val tempMin: Double? = null,
    @JsonProperty("temp_max")
    val tempMax: Double? = null,
    val main: String? = null,
    val description: String? = null,
)