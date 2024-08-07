package com.agb.citysearchapp.presentation.navigation

sealed class Screens(val route: String) {
    data object CitiesScreen : Screens("citiesScreen")
}