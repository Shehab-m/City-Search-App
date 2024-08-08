package com.agb.citysearchapp.presentation.cities

import android.content.Context
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.agb.citysearchapp.domain.model.City
import com.agb.citysearchapp.domain.usecase.GetCitiesUseCase
import com.agb.citysearchapp.domain.usecase.SearchCitiesUseCase
import com.agb.citysearchapp.presentation.base.BaseViewModel
import com.agb.citysearchapp.presentation.cities.mapper.toUIState
import com.agb.citysearchapp.presentation.util.MapUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CitiesViewModel @Inject constructor(
    private val getCitiesUseCase: GetCitiesUseCase,
    private val searchCitiesUseCase: SearchCitiesUseCase,
) : BaseViewModel<CitiesUiState, CitiesUiEffect>(CitiesUiState()), CitiesInteractionListener {

    init {
        getCities()
        observeSearchQuery()
    }

    private fun getCities() {
        updateState { it.copy(isLoading = true, isError = false, isSearchEnabled = false) }
        tryToExecute(
            { getCitiesUseCase() },
            ::onSuccessGetCities,
            ::onError
        )
    }

    private fun onSuccessGetCities(cities: List<City>) {
        val fetchedCities = cities.map { it.toUIState() }
        updateState {
            it.copy(
                isLoading = false,
                showingCities = fetchedCities,
                cities = fetchedCities,
                isSearchEnabled = true
            )
        }
    }

    @OptIn(FlowPreview::class)
    private fun observeSearchQuery() {
        collectFlow(state.value.searchInput.debounce(300).distinctUntilChanged()) { query ->
            if (query.isNotEmpty()) {
                searchCities(query)
            } else {
                updateState { it.copy(showingCities = it.cities) }
            }
            this
        }
    }

    private fun searchCities(query: String) {
        updateState { it.copy(isError = false) }
        tryToExecute(
            { searchCitiesUseCase(query) },
            ::onSuccessSearch,
            ::onError
        )
    }

    private fun onSuccessSearch(cities: List<City>) {
        updateState {
            it.copy(isLoading = false, showingCities = cities.map { it.toUIState() })
        }
    }

    private fun onError(error: Exception) {
        updateState { it.copy(isError = true, isLoading = false) }
        Log.e("TAG", "onError: ${error.message} ")
    }

    override fun onClickTryAgain() {
        getCities()
    }

    override fun openCityLocation(context: Context, latitude: Double, longitude: Double) {
        MapUtils.openLocationInMaps(context, latitude, longitude)
    }

    override fun onSearchQueryChanged(query: String) {
        viewModelScope.launch { _state.value.searchInput.emit(query) }
    }

}