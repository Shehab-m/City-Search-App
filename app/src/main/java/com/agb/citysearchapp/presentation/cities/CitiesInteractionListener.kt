package com.agb.citysearchapp.presentation.cities

import android.content.Context

interface CitiesInteractionListener {
    fun onClickTryAgain()
    fun openCityLocation(context: Context, latitude: Double, longitude: Double)
    fun onSearchQueryChanged(query: String)
}