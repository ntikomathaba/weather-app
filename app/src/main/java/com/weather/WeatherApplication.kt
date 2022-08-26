package com.weather

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class WeatherApplication : Application() {
    private var application: Application? = null

    override fun onCreate() {
        super.onCreate()
        application = this
    }

    fun getApplication(): Application? {
        return application
    }

}