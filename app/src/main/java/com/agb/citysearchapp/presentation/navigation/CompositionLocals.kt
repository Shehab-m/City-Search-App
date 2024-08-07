package com.agb.citysearchapp.presentation.navigation

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavHostController

/**
 * CompositionLocal for providing a NavHostController.
 *
 * This static CompositionLocal is used to provide a NavHostController to the Composables within its scope.
 * It throws an error if accessed without a NavHostController being provided.
 */
val LocalNavigationProvider = staticCompositionLocalOf<NavHostController> {
    error("No navigation host controller provided.")
}
