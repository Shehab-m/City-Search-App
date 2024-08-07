package com.agb.citysearchapp.data.repository.mapper

import com.agb.citysearchapp.data.model.CoordinatesDataModel
import com.agb.citysearchapp.domain.model.Coordinates

fun CoordinatesDataModel.toEntity(): Coordinates {
    return Coordinates(
        latitude = latitude ?: 0.0,
        longitude = longitude ?: 0.0
    )
}
