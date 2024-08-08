package com.agb.citysearchapp.presentation.cities

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.agb.citysearchapp.R
import com.agb.citysearchapp.presentation.base.EventHandler
import com.agb.citysearchapp.presentation.cities.composable.KItemCity
import com.agb.citysearchapp.presentation.composable.KAnimationContentState
import com.agb.citysearchapp.presentation.composable.KAnimationContent
import com.agb.citysearchapp.presentation.composable.KPlaceholder
import com.agb.citysearchapp.presentation.composable.KSearchField
import com.agb.citysearchapp.presentation.composable.KTopBar
import com.agb.citysearchapp.presentation.composable.Loading

@Composable
fun CitiesScreen(viewModel: CitiesViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current
    EventHandler(viewModel.effect) { effect, navController ->
        when (effect) {
            CitiesUiEffect.ShowErrorToast -> {
                Toast.makeText(
                    context,
                    context.getString(R.string.error_message),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
    CitiesContent(state, viewModel)
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "StateFlowValueCalledInComposition")
@Composable
fun CitiesContent(
    state: CitiesUiState, listener: CitiesInteractionListener
) {
    val context = LocalContext.current
    Scaffold { paddingValues ->
        KAnimationContent(
            state = state.isLoading, topBar = {
                KTopBar(
                    title = stringResource(R.string.world_cities),
                    paddingValues = paddingValues,
                    icon = painterResource(id = R.drawable.globe)
                )
                KSearchField(
                    modifier = Modifier.padding(
                        top = 8.dp,
                        bottom = 16.dp,
                        end = 20.dp,
                        start = 20.dp
                    ),
                    value = state.searchInput.value,
                    onValueChange = {
                        listener.onSearchQueryChanged(it)
                    },
                    enabled = state.isSearchEnabled
                )
            }, content = {
                KAnimationContentState(
                    state = state.showingCities.isNotEmpty(),
                    content = {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            contentPadding = PaddingValues(horizontal = 20.dp),
                        ) {
                            items(state.showingCities) { city ->
                                KItemCity(
                                    modifier = Modifier,
                                    city = city,
                                    onClick = {
                                        listener.openCityLocation(
                                            context,
                                            city.coordinates.latitude,
                                            city.coordinates.longitude
                                        )
                                    }
                                )
                            }
                        }
                    },
                    placeholderContent = {
                        KPlaceholder(
                            text = stringResource(R.string.no_cities_found),
                            icon = painterResource(id = R.drawable.globe_search)
                        )
                    }
                )
            },
            loadingContent = {
                Loading(state = true)
            },
            isError = state.isError,
            onClickTryAgain = {
                listener.onClickTryAgain()
            }
        )
    }
}

@Composable
@Preview
fun CitiesScreenPreview() {
    MaterialTheme {
        CitiesContent(
            state = CitiesUiState(), listener = object : CitiesInteractionListener {
                override fun onClickTryAgain() {}
                override fun openCityLocation(
                    context: Context,
                    latitude: Double,
                    longitude: Double
                ) {}
                override fun onSearchQueryChanged(query: String) {}
            }
        )
    }
}
