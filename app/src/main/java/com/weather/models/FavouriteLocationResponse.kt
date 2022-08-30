package com.weather.models

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.google.gson.annotations.SerializedName
import com.weather.util.FavouritesDeserializer

@JsonDeserialize
data class FavouriteLocationResponse(
    val list : List<FavouriteLocation>
)

data class FavouriteLocation(
    @SerializedName("name") var name: String? = null,
//    @SerializedName("local_names") var localNames: LocalNames? = LocalNames(),
    @SerializedName("lat") var lat: Double? = null,
    @SerializedName("lon") var lon: Double? = null,
    @SerializedName("country") var country: String? = null

)