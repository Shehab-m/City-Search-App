package com.agb.citysearchapp.presentation.cities.mapper

import com.agb.citysearchapp.domain.model.Coordinates
import com.agb.citysearchapp.presentation.cities.CoordinatesUIState

fun Coordinates.toUIState(): CoordinatesUIState {
    return CoordinatesUIState(
        latitude = latitude,
        longitude = longitude
    )
}
