package com.agb.citysearchapp.presentation.cities

import com.agb.citysearchapp.presentation.base.BaseUiEffect

sealed interface CitiesUiEffect : BaseUiEffect {
    data object ShowErrorToast : CitiesUiEffect
}