package com.agb.citysearchapp.data.repository.mapper

import com.agb.citysearchapp.data.model.CityDataModel
import com.agb.citysearchapp.domain.model.City
import com.agb.citysearchapp.domain.model.Coordinates

fun CityDataModel.toEntity(): City {
    return City(
        country = country ?: "",
        name = name ?: "",
        id = id ?: 0,
        coordinates = coordinates?.toEntity() ?: Coordinates(0.0, 0.0),
    )
}
