package com.agb.citysearchapp.presentation.cities

import com.agb.citysearchapp.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CitiesViewModel @Inject constructor() :
    BaseViewModel<CitiesUiState, CitiesUiEffect>(CitiesUiState()), CitiesInteractionListener {


}