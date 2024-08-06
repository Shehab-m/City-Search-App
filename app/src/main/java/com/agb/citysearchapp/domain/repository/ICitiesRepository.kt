package com.agb.citysearchapp.domain.repository

import com.agb.citysearchapp.domain.model.City

interface ICitiesRepository {
    suspend fun getCities(): List<City>
}