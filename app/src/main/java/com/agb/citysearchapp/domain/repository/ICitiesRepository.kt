package com.agb.citysearchapp.domain.repository

import com.agb.citysearchapp.domain.model.City

interface ICitiesRepository {
    suspend fun getCities(): List<City>
    suspend fun initializeTriCities(cities: List<City>)
    fun searchCities(prefix: String): List<City>
}