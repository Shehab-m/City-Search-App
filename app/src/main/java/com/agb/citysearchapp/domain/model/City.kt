package com.agb.citysearchapp.domain.model

data class City(
    val country: String,
    val name: String,
    val id: Int,
    val coordinates: Coordinates
)