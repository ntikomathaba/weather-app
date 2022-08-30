package com.weather.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.weather.domain.enums.SearchWidgetState
import com.weather.models.FavouriteLocation
import com.weather.repository.local.LocalWeatherRepository
import com.weather.repository.remote.FavouritesRepository
import com.weather.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class FavouritesViewModel @Inject constructor(
    private val favouritesRepository: FavouritesRepository,
    private val localWeatherRepository: LocalWeatherRepository,
) : ViewModel(){
    private val _searchWidgetState: MutableState<SearchWidgetState> = mutableStateOf(SearchWidgetState.CLOSED)
    val searchWidgetState = _searchWidgetState

    private val _searchTextState: MutableState<String> = mutableStateOf("")
    val searchTextState = _searchTextState

    val locations: MutableList<FavouriteLocation> = mutableListOf()

    fun updateSearchWidgetState(newValue: SearchWidgetState){
        _searchWidgetState.value = newValue
    }

    fun updateSearchTextState(newValue: String) {
        _searchTextState.value = newValue
    }

    fun searchLocation(q: String) {
        viewModelScope.launch {
        Timber.d("Searching $q")
            when(val response = favouritesRepository.searchFavouriteLocation(q)){
                is Resource.Success -> {
                    Timber.d("${response.data}")
                    response.data?.forEach {
                        locations.add(it)
                    }
                }
                is Resource.Error -> {

                }
            }
        }
    }
}