package com.weather.compose.navigationgraph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.weather.compose.screen.FavouriteScreen
import com.weather.compose.screen.MapScreen
import com.weather.compose.screen.Screen
import com.weather.compose.screen.WeatherAppHomeScreen
import com.weather.viewmodel.FavouritesViewModel
import com.weather.viewmodel.WeatherViewModel

@Composable
fun SetUpNavGraph(
    navController: NavHostController,
    weatherViewModel: WeatherViewModel,
    favViewModel: FavouritesViewModel
){
    NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {
        composable(
            route = Screen.HomeScreen.route,
        ) {
            WeatherAppHomeScreen(
                navController = navController,
                viewModel = weatherViewModel
            )
        }

        composable(
            route = Screen.FavouriteScreen.route
        ) {
            FavouriteScreen(
                navController = navController,
                favViewModel = favViewModel
            )
        }

        composable(
            route = Screen.MapScreen.route
        ) {
            MapScreen(
                navController = navController
            )
        }
    }

}