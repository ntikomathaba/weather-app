package com.weather.models

data class WeatherResponse(
	val visibility: Int? = null,
	val timezone: Int? = null,
	val main: Main? = null,
	val clouds: Clouds? = null,
	val sys: Sys? = null,
	val dt: Int? = null,
	val coord: Coord? = null,
	val weather: List<WeatherItem?>? = null,
	val name: String? = null,
	val cod: Int? = null,
	val id: Int? = null,
	val base: String? = null,
	val wind: Wind? = null
)

data class Main(
	val temp: Double? = null,
	val tempMin: Double? = null,
	val humidity: Int? = null,
	val pressure: Int? = null,
	val feelsLike: Double? = null,
	val tempMax: Double? = null
)

data class Coord(
	val lon: Int? = null,
	val lat: Int? = null
)

data class Sys(
	val country: String? = null,
	val sunrise: Int? = null,
	val sunset: Int? = null,
	val id: Int? = null,
	val type: Int? = null
)

data class Wind(
	val deg: Int? = null,
	val speed: Double? = null,
	val gust: Double? = null
)

data class Clouds(
	val all: Int? = null
)

data class WeatherItem(
	val icon: String? = null,
	val description: String? = null,
	val main: String? = null,
	val id: Int? = null
)

