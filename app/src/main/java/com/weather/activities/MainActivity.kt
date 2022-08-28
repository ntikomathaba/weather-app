package com.weather.activities

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.weather.compose.navigationgraph.SetUpNavGraph
import com.weather.ui.theme.MyWeatherAppTheme
import com.weather.viewmodel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: WeatherViewModel by viewModels()
    private lateinit var navController: NavHostController
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) {
            viewModel.loadWeatherInfo(fusedLocationProviderClient)
        }

        permissionLauncher.launch(arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
        ))

        setContent {
            MyWeatherAppTheme {
                navController = rememberNavController()
                SetUpNavGraph(
                    navController = navController,
                    viewModel = viewModel,
                )
            }
        }
    }

}
