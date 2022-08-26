package com.weather.compose.screen

sealed class Screen(val route: String) {
    object HomeScreen: Screen(route = "home_screen")
}