package com.agb.citysearchapp.data.source

import android.content.Context
import com.agb.citysearchapp.R
import com.agb.citysearchapp.data.model.CityDataModel
import com.agb.citysearchapp.data.util.JSONUtil
import com.google.gson.Gson
import javax.inject.Inject

class CitiesDataSource @Inject constructor(
    private val context: Context,
    private val gson: Gson
) : ICitiesDataSource {

    override suspend fun getCities(): List<CityDataModel> {
        val json = JSONUtil.loadJSONFromRawResource(context, R.raw.cities)
        return gson.fromJson(json, Array<CityDataModel>::class.java).toList()
    }

}