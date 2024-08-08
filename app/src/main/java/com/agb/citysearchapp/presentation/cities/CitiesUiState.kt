package com.agb.citysearchapp.presentation.cities

import kotlinx.coroutines.flow.MutableStateFlow

data class CitiesUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val searchInput: MutableStateFlow<String> = MutableStateFlow(""),
    val showingCities: List<CityUIState> = emptyList(),
    val cities: List<CityUIState> = emptyList(),
    val isSearchEnabled: Boolean = true
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

