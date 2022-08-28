package com.weather.compose.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun BottomAppBar(
    actions: @Composable RowScope.() -> Unit = {},
    navigationIcon: @Composable (() -> Unit)? = null,
    snackBarHost: @Composable (SnackbarHostState) -> Unit = { SnackbarHost(it) },
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        bottomBar = {

        }
    ) {

    }
}