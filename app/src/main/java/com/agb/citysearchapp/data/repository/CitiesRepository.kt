package com.agb.citysearchapp.data.repository

import com.agb.citysearchapp.data.datastructure.ITrieCity
import com.agb.citysearchapp.data.repository.mapper.toEntity
import com.agb.citysearchapp.data.source.ICitiesDataSource
import com.agb.citysearchapp.domain.model.City
import com.agb.citysearchapp.domain.repository.ICitiesRepository
import javax.inject.Inject

class CitiesRepository @Inject constructor(
    private val dataSource: ICitiesDataSource,
    private val trie: ITrieCity
) : ICitiesRepository {

    override suspend fun getCities(): List<City> {
        return dataSource.getCities().map { it.toEntity() }
    }

    override suspend fun initializeTriCities(cities: List<City>) {
        trie.insertBatch(cities)
    }

    override fun searchCities(prefix: String): List<City> {
        return trie.search(prefix)
    }

}