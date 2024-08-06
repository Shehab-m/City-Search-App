package com.agb.citysearchapp.domain.usecase

import com.agb.citysearchapp.domain.model.City
import com.agb.citysearchapp.domain.repository.ICitiesRepository
import javax.inject.Inject

class GetCitiesUseCase @Inject constructor(
    private val repository: ICitiesRepository
) {
    suspend operator fun invoke(): List<City> {
        return repository.getCities()
    }

}