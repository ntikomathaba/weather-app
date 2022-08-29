package com.weather.di

import android.app.Application
import androidx.room.Room
import com.weather.constants.DATABASE_NAME
import com.weather.db.WeatherDB
import com.weather.db.dao.LocalWeatherDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {

    @Provides
    @Singleton
    fun provideAstrocyteDB(application: Application): WeatherDB
    {
        return Room.databaseBuilder(
            application,
            WeatherDB::class.java, DATABASE_NAME
        )
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    @Singleton
    fun provideLocalWeatherDao(weatherDB: WeatherDB): LocalWeatherDao{
        return weatherDB.localWeatherDao()
    }
}