package com.agb.citysearchapp.data.source

import android.content.Context
import com.google.gson.Gson
import javax.inject.Inject

class CitiesDataSource @Inject constructor(
    private val context: Context,
    private val gson: Gson
) : ICitiesDataSource {



}