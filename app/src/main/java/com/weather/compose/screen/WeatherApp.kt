package com.weather.compose.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.weather.R
import com.weather.compose.components.WeatherBottomAppBar
import com.weather.domain.enum.WeatherCondition
import com.weather.models.WeatherState
import com.weather.ui.theme.CloudyColor
import com.weather.ui.theme.RainyColor
import com.weather.ui.theme.SunnyColor
import com.weather.viewmodel.WeatherViewModel

@Composable
fun WeatherAppHomeScreen(
    navController: NavController,
    viewModel: WeatherViewModel
) {
    val weather = viewModel.weather.collectAsState()
    val condition = weather.value.weatherInfo?.weather?.get(0)?.main

    val backgroundTint: Color = when(condition){
        WeatherCondition.CLEAR.condition -> {
            SunnyColor
        }
        WeatherCondition.RAIN.condition -> {
            RainyColor
        }
        WeatherCondition.CLOUDS.condition -> {
            CloudyColor
        }
        else -> {
            RainyColor
        }
    }
    val backgroundImage: Int = when(condition){
        WeatherCondition.CLEAR.condition -> {
            R.drawable.forest_sunny
        }
        WeatherCondition.RAIN.condition -> {
            R.drawable.forest_rainy
        }
        WeatherCondition.CLOUDS.condition -> {
            R.drawable.forest_cloudy
        }
        else -> {
            R.drawable.forest_sunny
        }
    }

    WeatherBottomAppBar(
        navController = navController,
        snackBarHost = { },
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = backgroundTint
                )
        ){
            Column {
                CurrentWeatherCard(
                    state = weather.value
                )
            }
        }
    }


}

@Composable
fun CurrentWeatherCard(state: WeatherState) {
    Column {
        state.weatherInfo?.let {
            Card(
                backgroundColor = Color.White,
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.padding(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Temperature ${it.main?.temp}")
                    Text(text = "Today is ${it.weather?.get(0)?.main}")
                    Text(text = "Min is ${it.main?.tempMin}")
                    Text(text = "Max is ${it.main?.tempMax}")
                }
            }
        }

    }
}