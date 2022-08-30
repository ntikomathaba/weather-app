package com.weather.ui.theme.compose.screen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val title: String, val route: String, val icon: ImageVector) {
    object HomeScreen: Screen(
        title = "Home",
        route = "home_screen",
        icon = Icons.Filled.Home
    )

    object FavouriteScreen: Screen(
        title = "Favourites",
        route = "favourite_screen",
        icon = Icons.Filled.Favorite
    )

    object MapScreen: Screen(
        title = "Maps",
        route = "map_screen",
        icon = Icons.Filled.LocationOn
    )
}