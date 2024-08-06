package com.agb.citysearchapp.di

import android.content.Context
import com.agb.citysearchapp.data.source.CitiesDataSource
import com.agb.citysearchapp.data.source.ICitiesDataSource
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideCityDataSource(@ApplicationContext context: Context, gson: Gson): ICitiesDataSource {
        return CitiesDataSource(context, gson)
    }

}