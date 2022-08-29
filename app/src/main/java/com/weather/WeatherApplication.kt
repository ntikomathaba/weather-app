package com.weather

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import timber.log.Timber.DebugTree

@HiltAndroidApp
class WeatherApplication : Application() {
    private var application: Application? = null

    override fun onCreate() {
        super.onCreate()
        application = this
        if (BuildConfig.DEBUG){
            Timber.plant(DebugTree())
        }
    }

    fun getApplication(): Application? {
        return application
    }

}