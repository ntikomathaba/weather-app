package com.weather.compose.screen

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.weather.compose.components.WeatherBottomAppBar
import com.weather.viewmodel.WeatherViewModel

@Composable
fun FavouriteScreen(
    navController: NavController,
    viewModel: WeatherViewModel
) {
    WeatherBottomAppBar(
        navController = navController,
        snackBarHost = { },
    ) {
        Text(text = "Favourite screen")
    }
}
