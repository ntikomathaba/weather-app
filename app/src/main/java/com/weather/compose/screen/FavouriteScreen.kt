package com.weather.compose.screen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.weather.compose.components.SearchAppBar
import com.weather.compose.components.WeatherBottomAppBar
import com.weather.domain.enums.SearchWidgetState
import com.weather.viewmodel.FavouritesViewModel

@Composable
fun FavouriteScreen(
    navController: NavController,
    favViewModel: FavouritesViewModel
) {
    WeatherBottomAppBar(
        navController = navController,
        snackBarHost = { },
        topBar = {
            val searchWidgetState by favViewModel.searchWidgetState
            val searchTextState by favViewModel.searchTextState
            MainAppBar(
                searchWidgetState = searchWidgetState,
                searchTextState = searchTextState,
                onTextChange = { favViewModel.updateSearchTextState(it) },
                onCloseClicked = { favViewModel.updateSearchWidgetState(newValue = SearchWidgetState.CLOSED) },
                onSearchClicked = { favViewModel.searchLocation(it) },
                onSearchTriggered = { favViewModel.updateSearchWidgetState(newValue = SearchWidgetState.OPENED) }
            )
        }
    ) {
        LazyColumn{
//            itemsIndexed(){ index, city ->
//
//            }
        }
    }
}

@Composable
fun MainAppBar(
    searchWidgetState: SearchWidgetState,
    searchTextState: String,
    onTextChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: (String) -> Unit,
    onSearchTriggered: () -> Unit
) {
    when (searchWidgetState) {
        SearchWidgetState.CLOSED -> {
            DefaultAppBar(
                onSearchClicked = onSearchTriggered
            )
        }
        SearchWidgetState.OPENED -> {
            SearchAppBar(
                text = searchTextState,
                onTextChange = onTextChange,
                onCloseClicked = onCloseClicked,
                onSearchClicked = onSearchClicked
            )
        }
    }
}

@Composable
fun DefaultAppBar(onSearchClicked: () -> Unit) {
    TopAppBar(
        title = {
            Text(
                text = "Favourite Location"
            )
        },
        actions = {
            IconButton(
                onClick = { onSearchClicked() }
            ) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search Icon",
                    tint = Color.White
                )
            }
        }
    )
}