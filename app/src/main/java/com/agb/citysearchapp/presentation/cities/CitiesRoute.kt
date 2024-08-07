package com.agb.citysearchapp.presentation.cities

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.agb.citysearchapp.presentation.navigation.Screens

private val ROUTE = Screens.CitiesScreen.route

fun NavGraphBuilder.citiesRoute() {
    composable(ROUTE) {
        CitiesScreen()
    }
}