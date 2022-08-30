package com.weather.ui.theme.compose.screen

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.weather.ui.theme.compose.components.WeatherBottomAppBar

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