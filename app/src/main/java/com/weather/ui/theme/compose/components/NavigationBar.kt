package com.weather.ui.theme.compose.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.weather.ui.theme.compose.screen.Screen

@Composable
fun WeatherBottomAppBar(
    navController: NavController,
    snackBarHost: @Composable (SnackbarHostState) -> Unit = { SnackbarHost(it) },
    topBar: @Composable () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit
) {
    val navItems = listOf(
        Screen.HomeScreen,
        Screen.FavouriteScreen,
        Screen.MapScreen
    )

    Scaffold(
        topBar = topBar,
        bottomBar = {
            BottomAppBar(
                backgroundColor = Color.White,
                elevation = 4.dp
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                navItems.forEach { item ->
                    BottomNavigationItem(
                        icon = { Icon(imageVector = item.icon, contentDescription = "${item.title} icon") },
                        label = { Text(text = item.title)},
                        selectedContentColor = Color.Black,
                        unselectedContentColor = Color.Black.copy(0.4f),
                        alwaysShowLabel = true,
                        selected = currentRoute == item.route,
                        onClick = {
                            navController.navigate(item.route) {
                                navController.graph.startDestinationRoute?.let { screen_route ->
                                    popUpTo(screen_route) {
                                        saveState = true
                                    }
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        },
        snackbarHost = snackBarHost,
        content = content
    )
}