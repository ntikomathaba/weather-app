package com.weather.compose.components

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun WeatherProgressIndicator() {
    CircularProgressIndicator(
        color = Color.LightGray
    )

}