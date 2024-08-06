package com.agb.citysearchapp.presentation.cities

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.agb.citysearchapp.presentation.base.EventHandler

@Composable
fun CitiesScreen(viewModel: CitiesViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()
    EventHandler(viewModel.effect) { effect, navController ->
        when (effect) {

            else -> {}
        }
    }
    CitiesContent(state, viewModel)
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CitiesContent(
    state: CitiesUiState, listener: CitiesInteractionListener
) {
    Scaffold { paddingValues ->

    }
}

@Composable
@Preview
fun CitiesScreenPreview() {
    MaterialTheme {
        CitiesContent(
            state = CitiesUiState(),
            listener = object : CitiesInteractionListener {

            }
        )
    }
}
