package com.agb.citysearchapp.presentation.cities

import com.agb.citysearchapp.domain.model.City

data class CitiesUiState(
    val isLoading: Boolean = false,
    val cities: List<City> = emptyList()
)

data class CityUIState(
    val country: String = "",
    val name: String = "",
    val id: Int = 0,
    val coordinates: CoordinatesUIState = CoordinatesUIState()
)

data class CoordinatesUIState(
    val longitude: Double = 0.0,
    val latitude: Double = 0.0
)

