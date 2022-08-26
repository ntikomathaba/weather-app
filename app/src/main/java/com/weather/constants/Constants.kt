package com.weather.constants

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

const val BASE_URL ="https://api.openweathermap.org/"
const val DATABASE_NAME = "WeatherDB"

private val mainDispatcher: CoroutineDispatcher
    get() = Dispatchers.Main

private val ioDispatcher: CoroutineDispatcher
    get() = Dispatchers.IO