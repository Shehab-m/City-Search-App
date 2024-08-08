package com.agb.citysearchapp.domain.usecase

import com.agb.citysearchapp.domain.model.City
import com.agb.citysearchapp.domain.repository.ICitiesRepository
import javax.inject.Inject

class SearchCitiesUseCase @Inject constructor(
    private val repository: ICitiesRepository
) {

    /**
     * Searches for cities that match the given prefix and returns them in alphabetical order.
     *
     * Searches using Trie data structure to efficiently search for cities that start
     * with the provided prefix. The search results are then sorted alphabetically, first by city
     * name and then by country code if there are multiple cities with the same name.
     *
     * @param prefix The prefix string used to filter cities in the Trie.
     * @return A list of cities that match the given prefix, sorted alphabetically by city name
     * and country code.
     */
    operator fun invoke(prefix: String): List<City> {
        val cities = repository.searchCities(prefix)
        return getSortedCities(cities)
    }

    /**
     * Sorts a list of cities by city name and country in a case-insensitive manner.
     *
     * This function sorts the given list of cities primarily by city name and secondarily
     * by country. Both sorting criteria are applied in a case-insensitive manner by converting
     * names and countries to lowercase. The resulting list will be ordered alphabetically with
     * city names appearing before country names.
     *
     * @param cities The list of cities to be sorted.
     * @return A new list of cities sorted alphabetically by city name and then by country.
     */
    private fun getSortedCities(cities: List<City>): List<City> {
        return cities.sortedWith(compareBy({ it.name.lowercase() }, { it.country.lowercase() }))
    }
}