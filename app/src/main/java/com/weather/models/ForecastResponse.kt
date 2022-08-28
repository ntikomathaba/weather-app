package com.weather.models

import com.fasterxml.jackson.annotation.JsonProperty

data class ForecastResponse(
	@JsonProperty("temp_max")
	val city: City? = null,
	val cnt: Int? = null,
	val cod: String? = null,
	val message: Int? = null,
	val list: List<ListItem?>? = null
)

data class ListItem(
	val dt: Int? = null,
	val pop: Int? = null,
	val visibility: Int? = null,
	val dtTxt: String? = null,
	val weather: List<WeatherItem?>? = null,
	val main: Main? = null,
	val clouds: Clouds? = null,
	val sys: Sys? = null,
	val wind: Wind? = null
)

data class City(
	val country: String? = null,
	val coord: Coord? = null,
	val sunrise: Int? = null,
	val timezone: Int? = null,
	val sunset: Int? = null,
	val name: String? = null,
	val id: Int? = null,
	val population: Int? = null
)

