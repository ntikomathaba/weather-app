package com.weather.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fasterxml.jackson.databind.ObjectMapper
import com.weather.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository,
    val objectMapper: ObjectMapper
) : ViewModel(){

    init {
        viewModelScope.launch {
            getWeather()
        }
    }

    private suspend fun getWeather() {
        weatherRepository.getWeather(
            lat = 35.0,
            long = 39.0
        )
    }
}