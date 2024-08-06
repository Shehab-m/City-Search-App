package com.agb.citysearchapp.data.model

import com.google.gson.annotations.SerializedName

data class CoordinatesDataModel(
    @SerializedName("lon")
    val longitude: Double?,
    @SerializedName("lat")
    val latitude: Double?
)