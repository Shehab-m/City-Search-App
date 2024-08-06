package com.agb.citysearchapp.data.source

import com.agb.citysearchapp.data.model.CityDataModel

interface ICitiesDataSource {
    suspend fun getCities(): List<CityDataModel>
}