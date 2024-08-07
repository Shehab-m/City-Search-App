package com.agb.citysearchapp.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.agb.citysearchapp.presentation.cities.citiesRoute

fun NavGraphBuilder.citiesNavGraph() {
    navigation(
        startDestination = Screens.CitiesScreen.route,
        route = Graph.CITIES
    ) {
        citiesRoute()
    }
}