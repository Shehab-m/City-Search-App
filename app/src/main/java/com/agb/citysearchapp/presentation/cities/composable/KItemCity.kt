package com.agb.citysearchapp.presentation.cities.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.agb.citysearchapp.R
import com.agb.citysearchapp.presentation.cities.CityUIState
import com.agb.citysearchapp.presentation.cities.CoordinatesUIState

/**
 * Displays a city item with name, country, and coordinates for better user experience.
 *
 * The structure of this composable ensures a clear presentation of city details:
 * - Row layout with icon and text for visual clarity and alignment.
 * - Clickable column that contains the row to support interaction, showing city location on a map.
 * - Distinct styles for city name and country for readability.
 * - Coordinates shown as a subtitle to provide detailed information at a glance.
 * - A divider at the bottom visually separates each city item.
 *
 * @param modifier Modifier to customize the appearance and layout.
 * @param city The city information to display, encapsulated in a CityUIState.
 * @param onClick Lambda to handle click events on the city item.
 * @param backgroundColor Background color of the city item.
 */
@Composable
fun KItemCity(
    modifier: Modifier = Modifier,
    city: CityUIState,
    onClick: () -> Unit,
    backgroundColor: Color = MaterialTheme.colorScheme.background,
) {
    Column(modifier = modifier.background(backgroundColor).clickable { onClick() }) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                modifier = Modifier.size(24.dp),
                painter = painterResource(id = R.drawable.location_icon),
                contentDescription = "Location icon",
                tint = MaterialTheme.colorScheme.primary
            )
            Column(
                modifier = Modifier,
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                BasicText(
                    modifier = Modifier,
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = MaterialTheme.colorScheme.onTertiary,
                                fontStyle = MaterialTheme.typography.bodyLarge.fontStyle,
                            )
                        ) {
                            append("${city.name}, ")
                        }
                        withStyle(
                            style = SpanStyle(
                                color = MaterialTheme.colorScheme.onTertiary,
                                fontStyle = MaterialTheme.typography.bodyMedium.fontStyle,
                            )
                        ) {
                            append(city.country)
                        }
                    },
                )
                Text(
                    text = "Coordinates: ${city.coordinates.latitude}, ${city.coordinates.longitude}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onTertiary,
                    textAlign = TextAlign.Center
                )
            }
        }
        HorizontalDivider(modifier = Modifier.padding(top = 8.dp))
    }
}

@Preview
@Composable
fun KItemCityPreview() {
    KItemCity(
        city = CityUIState(
            name = "Cairo",
            country = "EG",
            coordinates = CoordinatesUIState(12.0, 3232.3)
        ),
        onClick = {}
    )
}