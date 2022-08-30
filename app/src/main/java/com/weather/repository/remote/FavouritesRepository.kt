package com.weather.repository.remote

import com.weather.api.WeatherRestApiService
import com.weather.constants.SEARCH_LIMIT
import com.weather.models.FavouriteLocation
import com.weather.util.Resource
import javax.inject.Inject
import javax.inject.Named

class FavouritesRepository @Inject constructor(
    private val weatherRestApiService: WeatherRestApiService,
    @Named("apiKey") private val apiKey: String,
){
    suspend fun searchFavouriteLocation(city: String): Resource<List<FavouriteLocation>> {
        return try {
            Resource.Success(
                data = weatherRestApiService.searchFavouriteLocation(
                    appid = apiKey,
                    q = city,
                    limit = SEARCH_LIMIT
                )
            )
        } catch(e: Exception){
            e.printStackTrace()
            Resource.Error(e.message ?: "Error has occurred")
        }
    }
}