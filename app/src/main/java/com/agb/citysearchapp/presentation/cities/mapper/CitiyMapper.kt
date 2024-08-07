package com.agb.citysearchapp.presentation.cities.mapper

import com.agb.citysearchapp.domain.model.City
import com.agb.citysearchapp.presentation.cities.CityUIState

fun City.toUIState(): CityUIState {
    return CityUIState(
        country = country,
        name = name,
        id = id,
        coordinates = coordinates.toUIState(),
    )
}
