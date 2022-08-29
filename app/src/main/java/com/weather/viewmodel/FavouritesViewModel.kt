package com.weather.viewmodel

import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.fasterxml.jackson.databind.ObjectMapper
import com.weather.domain.enums.SearchWidgetState
import com.weather.repository.local.LocalWeatherRepository
import com.weather.repository.remote.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavouritesViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val localWeatherRepository: LocalWeatherRepository,
    val objectMapper: ObjectMapper,
    private val application: Application
) : ViewModel(){
    private val _searchWidgetState: MutableState<SearchWidgetState> = mutableStateOf(SearchWidgetState.CLOSED)
    val searchWidgetState = _searchWidgetState

    private val _searchTextState: MutableState<String> = mutableStateOf("")
    val searchTextState = _searchTextState

    fun updateSearchWidgetState(newValue: SearchWidgetState){
        _searchWidgetState.value = newValue
    }

    fun updateSearchTextState(newValue: String) {
        _searchTextState.value = newValue
    }
}