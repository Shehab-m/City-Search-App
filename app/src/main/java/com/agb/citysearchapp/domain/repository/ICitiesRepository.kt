package com.agb.citysearchapp.domain.repository

import com.agb.citysearchapp.data.model.CityDataModel

interface ICitiesRepository {
    suspend fun getCities(): List<CityDataModel>
}