package com.weather.ui.theme.compose.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.weather.R
import com.weather.ui.theme.compose.components.WeatherBottomAppBar
import com.weather.domain.enums.DayOfWeek
import com.weather.domain.enums.WeatherCondition
import com.weather.models.ForecastResponse
import com.weather.models.WeatherState
import com.weather.ui.theme.CloudyColor
import com.weather.ui.theme.RainyColor
import com.weather.ui.theme.SunnyColor
import com.weather.viewmodel.WeatherViewModel
import kotlin.math.roundToInt

@Composable
fun WeatherAppHomeScreen(
    navController: NavController,
    viewModel: WeatherViewModel
) {
    val weather = viewModel.weather.collectAsState()
    val condition = weather.value.weatherInfo?.weather?.get(0)?.main

    val forecast = viewModel.foreCastInfo.collectAsState()

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
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(0.5f)
                ) {
                    CurrentWeatherCard(
                        state = weather.value,
                        modifier = Modifier.zIndex(1f)
                    )
                    Image(
                        painter = painterResource(id = backgroundImage),
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .zIndex(-1f)
                            .fillMaxSize()
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(0.5f)
                ) {
                    ForeCastCard(
                        state = forecast.value,
                        modifier = Modifier
                    )
                }
            }
        }
    }


}

@Composable
fun CurrentWeatherCard(
    state: WeatherState,
    modifier: Modifier = Modifier
) {
    Column {
        state.weatherInfo?.let {
            Card(
                backgroundColor = Color.Transparent,
//                shape = RoundedCornerShape(10.dp),
                elevation = 0.dp,
                modifier = Modifier.fillMaxSize().padding(16.dp).border(border = BorderStroke(0.dp, Color.Transparent))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(0.8f),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            buildAnnotatedString {
                                withStyle(style = ParagraphStyle(lineHeight = 40.sp)) {
                                    withStyle(style = SpanStyle(color = Color.White, fontSize = 60.sp)) {
                                        append("${it.main?.temp?.roundToInt()} °C\n")
                                    }
                                    withStyle(
                                        style = SpanStyle(
                                            fontWeight = FontWeight.Bold,
                                            color = Color.White
                                        )
                                    ) {
                                        append("${it.weather?.get(0)?.main}\n")
                                    }
                                }
                            },
                            modifier = Modifier.weight(0.33f),
                            textAlign = TextAlign.Center
                        )
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(0.2f),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.Bottom
                    ) {
                        Text(
                            buildAnnotatedString {
                                withStyle(style = ParagraphStyle(lineHeight = 20.sp)) {
                                    withStyle(style = SpanStyle(color = Color.White)) {
                                        append("${it.main?.tempMin?.roundToInt()} °C\n")
                                    }
                                    withStyle(
                                        style = SpanStyle(
                                            fontWeight = FontWeight.Bold,
                                            color = Color.White
                                        )
                                    ) {
                                        append("min\n")
                                    }
                                }
                            },
                            modifier = Modifier.weight(0.33f),
                            textAlign = TextAlign.Center
                        )

                        Text(
                            buildAnnotatedString {
                                withStyle(style = ParagraphStyle(lineHeight = 20.sp)) {
                                    withStyle(style = SpanStyle(color = Color.White)) {
                                        append("${it.main?.tempMin?.roundToInt()} °C\n")
                                    }
                                    withStyle(
                                        style = SpanStyle(
                                            fontWeight = FontWeight.Bold,
                                            color = Color.White
                                        )
                                    ) {
                                        append("current\n")
                                    }
                                }
                            },
                            modifier = Modifier.weight(0.33f),
                            textAlign = TextAlign.Center
                        )

                        Text(
                            buildAnnotatedString {
                                withStyle(style = ParagraphStyle(lineHeight = 20.sp)) {
                                    withStyle(style = SpanStyle(color = Color.White)) {
                                        append("${it.main?.tempMax?.roundToInt()} °C\n")
                                    }
                                    withStyle(
                                        style = SpanStyle(
                                            fontWeight = FontWeight.Bold,
                                            color = Color.White
                                        )
                                    ) {
                                        append("max\n")
                                    }
                                }
                            },
                            modifier = Modifier
                                .weight(0.33f),
                            textAlign = TextAlign.Center
                        )
//                        Text(text = "Min is ${it.main?.tempMin} °C")
//                        Text(text = "Today is ${it.weather?.get(0)?.main}")
//                        Text(text = "Max is ${it.main?.tempMax} °C")
                    }
                }
            }
        }

    }
}

@Composable
fun ForeCastCard(
    state: ForecastResponse,
    modifier: Modifier = Modifier
) {

    Column {
        state.list?.take(5)?.forEachIndexed { dayOfWeek, forecastItem ->
            val icon: Int = when(forecastItem?.weather?.get(0)?.main){
                WeatherCondition.CLEAR.condition -> {
                    R.drawable.clear_3x
                }
                WeatherCondition.RAIN.condition -> {
                    R.drawable.rain_3x
                }
                WeatherCondition.CLOUDS.condition -> {
                    R.drawable.partlysunny_3x
                }
                else -> {
                    R.drawable.clear_3x
                }
            }

            when(dayOfWeek){
                0 -> {
                    DayOfWeekItem(
                        dayOfWeek = DayOfWeek.MONDAY.day,
                        icon = icon,
                        temp = forecastItem?.main?.temp!!
                    )
                }
                1 -> {
                    DayOfWeekItem(
                        dayOfWeek = DayOfWeek.TUESDAY.day,
                        icon = icon,
                        temp = forecastItem?.main?.temp!!
                    )
                }
                2 -> {
                    DayOfWeekItem(
                        dayOfWeek = DayOfWeek.WEDNESDAY.day,
                        icon = icon,
                        temp = forecastItem?.main?.temp!!
                    )
                }
                3 -> {
                    DayOfWeekItem(
                        dayOfWeek = DayOfWeek.THURSDAY.day,
                        icon = icon,
                        temp = forecastItem?.main?.temp!!
                    )
                }
                4 -> {
                    DayOfWeekItem(
                        dayOfWeek = DayOfWeek.FRIDAY.day,
                        icon = icon,
                        temp = forecastItem?.main?.temp!!
                    )
                }
                5 -> {
                    DayOfWeekItem(
                        dayOfWeek = DayOfWeek.SATURDAY.day,
                        icon = icon,
                        temp = forecastItem?.main?.temp!!
                    )
                }
                6 -> {
                    DayOfWeekItem(
                        dayOfWeek = DayOfWeek.SUNDAY.day,
                        icon = icon,
                        temp = forecastItem?.main?.temp!!
                    )
                }
            }
        }
    }
}

@Composable
fun DayOfWeekItem(
    dayOfWeek: String,
    icon: Int,
    temp: Double
) {
    Row(
        modifier = Modifier.padding(top = 10.dp, bottom = 20.dp, end = 4.dp, start = 4.dp)
    ) {
        Text(text = dayOfWeek, modifier = Modifier.weight(0.5f), color = Color.White)
        Icon(painter =  painterResource(id = icon), contentDescription = "", modifier = Modifier.weight(0.25f).size(20.dp), tint = Color.White)
        Spacer(modifier = Modifier.weight(0.1f))
        Text(text = "${temp.roundToInt()}°C", modifier = Modifier.weight(0.15f), color = Color.White)
    }
}

@Preview
@Composable
fun BackgroundImage() {
    Column {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(0.5f)
        ) {
            Card( Modifier.zIndex(1f)){
                Text(text = "random text")
            }
            Image(
                painter = painterResource(id = R.drawable.forest_sunny),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .zIndex(-1f)
                    .fillMaxSize()
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(0.5f)
        ) {
            Card( Modifier.zIndex(1f)){
                Text(text = "random text")
            }

        }
    }

}