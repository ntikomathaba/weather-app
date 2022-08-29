package com.weather.models

import com.weather.domain.enum.WeatherCondition
import java.time.LocalDateTime

data class WeatherState(
    val weatherInfo: WeatherResponse? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val weatherForecast: ForecastResponse? = null
)

data class WeatherForecast(
    val weatherDataPerDay: Map<Int, List<WeatherData>>,
    val currentWeatherData: WeatherData?
)

data class WeatherData(
    val time: LocalDateTime,
    val temperatureCelsius: Double,
    val pressure: Double,
    val windSpeed: Double,
    val humidity: Double,
    val weatherType: WeatherCondition
)