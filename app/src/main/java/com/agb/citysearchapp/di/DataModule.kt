package com.agb.citysearchapp.di

import com.agb.citysearchapp.data.datastructure.ITrieCity
import com.agb.citysearchapp.data.datastructure.TrieCity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideTrie(): ITrieCity {
        return TrieCity()
    }
}
