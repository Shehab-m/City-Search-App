package com.agb.citysearchapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost

@Composable
fun AppNavHost() {
    val navController = LocalNavigationProvider.current
    NavHost(
        navController = navController,
        startDestination = Graph.CITIES
    ) {
        citiesNavGraph()
    }
}

object Graph {
    const val CITIES = "cities_graph"
}