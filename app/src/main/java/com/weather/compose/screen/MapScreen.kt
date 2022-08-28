package com.weather.compose.screen

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.weather.compose.components.WeatherBottomAppBar
import com.weather.viewmodel.MapsViewModel

@Composable
fun MapScreen(
    navController: NavController,
    viewModel: MapsViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    WeatherBottomAppBar(
        navController = navController,
        snackBarHost = { },
    ) {
        Text(text = "Map screen")
    }
}