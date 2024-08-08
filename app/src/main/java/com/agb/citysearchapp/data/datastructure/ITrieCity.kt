package com.agb.citysearchapp.data.datastructure

import com.agb.citysearchapp.domain.model.City

interface ITrieCity {
    suspend fun insertBatch(cities: List<City>)
    fun search(prefix: String): List<City>
}
