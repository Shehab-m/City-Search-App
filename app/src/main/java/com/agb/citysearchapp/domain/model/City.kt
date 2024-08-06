package com.agb.citysearchapp.domain.model

// Represents a city with its attributes
data class City(
    val country: String,
    val name: String,
    val id: Int,
    val coordinates: Coordinates
)