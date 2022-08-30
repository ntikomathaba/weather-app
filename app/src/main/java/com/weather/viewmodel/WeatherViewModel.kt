package com.weather.viewmodel

import android.Manifest
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.location.FusedLocationProviderClient
import com.weather.db.entities.Coordinates
import com.weather.db.entities.Weather
import com.weather.models.ForecastResponse
import com.weather.models.WeatherState
import com.weather.repository.local.LocalWeatherRepository
import com.weather.repository.remote.WeatherRepository
import com.weather.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val localWeatherRepository: LocalWeatherRepository,
    private val application: Application
) : ViewModel(){

    private lateinit var locationTrackerClient: FusedLocationProviderClient

    private var _weatherInfo = MutableStateFlow(WeatherState())
    val weather = _weatherInfo.asStateFlow()

    private var _foreCastInfo = MutableStateFlow(ForecastResponse())
    val foreCastInfo = _foreCastInfo.asStateFlow()

    private var localWeather = MutableStateFlow(Weather())


    fun getWeatherInfo(){
       val localWeather = localWeatherRepository.getWeatherLatestWeather()
    }

    fun loadWeatherInfo(fusedLocationProviderClient: FusedLocationProviderClient) {
        viewModelScope.launch {
            locationTrackerClient = fusedLocationProviderClient
            _weatherInfo.value = _weatherInfo.value.copy(
                isLoading = true,
                error = null
            )
            getCurrentLocation()?.let {  location ->
                when(val result = weatherRepository.getWeather(
                    lat = location.latitude,
                    long = location.longitude
                )) {
                    is Resource.Success -> {
                        _weatherInfo.value = _weatherInfo.value.copy(
                            weatherInfo = result.data,
                            isLoading = false,
                            error = null
                        )
                        _weatherInfo.value = _weatherInfo.value
                    }
                    is Resource.Error -> {
                        _weatherInfo.value = _weatherInfo.value.copy(
                            weatherInfo = result.data,
                            isLoading = false,
                            error = result.message
                        )
                        _weatherInfo.value = _weatherInfo.value
                    }
                }



                val coordinates = Coordinates(latitude = location.latitude, longitude = location.longitude)
                localWeatherRepository.insertCoordinates(
                    coordinates = coordinates
                )

                val weather = Weather(
                    temp = _weatherInfo.value.weatherInfo?.main?.temp,
                    tempMin = _weatherInfo.value.weatherInfo?.main?.tempMin,
                    tempMax = _weatherInfo.value.weatherInfo?.main?.tempMax,
                    description = _weatherInfo.value.weatherInfo?.weather?.get(0)?.description,
                    main = _weatherInfo.value.weatherInfo?.weather?.get(0)?.main
                )
                localWeatherRepository.insertWeather(weather)

                _foreCastInfo.value = weatherRepository.getForecast(lat = location.latitude, long = location.longitude)


            }
        }
    }

    private suspend fun getCurrentLocation(): Location? {
        val hasAccessFineLocationPermission = ContextCompat.checkSelfPermission(
            application,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        val hasAccessCoarseLocationPermission = ContextCompat.checkSelfPermission(
            application,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        val locationManager = application.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val isGpsEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.GPS_PROVIDER)

        if(!hasAccessCoarseLocationPermission || !hasAccessFineLocationPermission || !isGpsEnabled) {
            return null
        }

        return suspendCancellableCoroutine { cont ->
            locationTrackerClient.lastLocation.apply {
                if(isComplete) {
                    if(isSuccessful) {
                        cont.resume(result)
                    } else {
                        cont.resume(null)
                    }
                    return@suspendCancellableCoroutine
                }
                addOnSuccessListener {
                    cont.resume(it)
                }
                addOnFailureListener {
                    cont.resume(null)
                }
                addOnCanceledListener {
                    cont.cancel()
                }
            }
        }
    }
}