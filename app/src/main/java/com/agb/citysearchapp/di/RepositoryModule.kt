package com.agb.citysearchapp.di

import com.agb.citysearchapp.domain.repository.ICitiesRepository
import com.agb.citysearchapp.data.repository.CitiesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideCitiesRepository(repository: CitiesRepository): ICitiesRepository

}