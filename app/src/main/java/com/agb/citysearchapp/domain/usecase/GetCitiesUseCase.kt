package com.agb.citysearchapp.domain.usecase

import com.agb.citysearchapp.domain.model.City
import com.agb.citysearchapp.domain.repository.ICitiesRepository
import javax.inject.Inject

class GetCitiesUseCase @Inject constructor(
    private val repository: ICitiesRepository
) {
    /**
     * Initializes the Trie data structure with a list of cities and returns a sorted list of cities.
     *
     * This suspend function retrieves a list of cities from the repository, initializes the Trie
     * data structure with these cities for efficient prefix-based searching, and returns the
     * sorted list of cities. The sorting is performed by the `getSortedCities` function, which ensures
     * that cities are listed alphabetically by city name first and then by country.
     *
     * @return A list of cities sorted alphabetically by city name and country.
     */
    suspend operator fun invoke(): List<City> {
        val cities = repository.getCities()
        repository.initializeTriCities(cities)
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