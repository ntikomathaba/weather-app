package com.weather.viewmodel

import android.location.Location
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fasterxml.jackson.databind.ObjectMapper
import com.weather.db.dao.LocalWeatherDao
import com.weather.repository.local.LocalWeatherRepository
import com.weather.repository.remote.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository,
    val objectMapper: ObjectMapper
) : ViewModel(){



    fun setCurrentLocation(location: Location?) {
        if (location != null) {
            viewModelScope.launch {
                val weatherResponse = weatherRepository.getWeather(
                    location.latitude,
                    location.longitude
                )

//                localWeatherRepository.insertWeather(weatherResponse)
            }
        }
    }
}