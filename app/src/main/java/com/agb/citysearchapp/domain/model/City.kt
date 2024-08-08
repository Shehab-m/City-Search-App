package com.agb.citysearchapp.domain.model

// Represents a city with its attributes
data class City(
    val name: String,
    val country: String,
    val id: Int,
    val coordinates: Coordinates
) {
    override fun toString(): String = "$name, $country"
}