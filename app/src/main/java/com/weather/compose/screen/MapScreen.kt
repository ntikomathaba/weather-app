package com.weather.compose.screen

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.weather.compose.components.WeatherBottomAppBar

@Composable
fun MapScreen(
    navController: NavController,
) {
    WeatherBottomAppBar(
        navController = navController,
        snackBarHost = { },
    ) {
        Text(text = "Map screen")
    }
}