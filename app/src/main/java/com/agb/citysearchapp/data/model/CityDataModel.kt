package com.agb.citysearchapp.data.model

import com.google.gson.annotations.SerializedName

data class CityDataModel(
    @SerializedName("country")
    val country: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("_id")
    val id: Int?,
    @SerializedName("coord")
    val coordinates: CoordinatesDataModel?
)

