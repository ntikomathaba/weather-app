package com.weather.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.weather.compose.navigationgraph.SetUpNavGraph
import com.weather.ui.theme.MyWeatherAppTheme
import com.weather.viewmodel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: WeatherViewModel by viewModels()
    private lateinit var navController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyWeatherAppTheme {
                navController = rememberNavController()
                SetUpNavGraph(
                    navController = navController,
                    viewModel = viewModel
                )
            }
        }
    }
}
