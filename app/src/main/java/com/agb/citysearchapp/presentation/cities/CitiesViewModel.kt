package com.agb.citysearchapp.presentation.cities

import android.util.Log
import com.agb.citysearchapp.domain.model.City
import com.agb.citysearchapp.domain.usecase.GetCitiesUseCase
import com.agb.citysearchapp.presentation.base.BaseViewModel
import com.agb.citysearchapp.presentation.cities.mapper.toUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CitiesViewModel @Inject constructor(
    private val getCitiesUseCase: GetCitiesUseCase
) : BaseViewModel<CitiesUiState, CitiesUiEffect>(CitiesUiState()), CitiesInteractionListener {

    init {
        getCities()
    }

    private fun getCities() {
        updateState { it.copy(isLoading = false, isError = false) }
        tryToExecute(
            { getCitiesUseCase() },
            ::onSuccessGetCities,
            ::onError
        )
    }

    private fun onSuccessGetCities(cities: List<City>) {
        updateState { it.copy(isLoading = false, cities = cities.map { it.toUIState() }) }
    }

    private fun onError(error: Exception) {
        updateState { it.copy(isError = true, isLoading = false) }
        Log.d("TAG", "onError: ${error.message} ")
    }

    override fun onClickTryAgain() {
        getCities()
    }

}