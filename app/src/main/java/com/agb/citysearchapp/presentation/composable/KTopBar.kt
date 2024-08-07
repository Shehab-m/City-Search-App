package com.agb.citysearchapp.presentation.composable

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@SuppressLint("UnrememberedMutableState")
@ExperimentalMaterial3Api
@Composable
fun KTopBar(
    modifier: Modifier = Modifier,
    title: String,
    paddingValues: PaddingValues,
    titleColor: Color = MaterialTheme.colorScheme.secondary,
    horizontalPadding: Dp = 20.dp,
) {
    Box(
        modifier = modifier.padding(vertical = 16.dp, horizontal = horizontalPadding).fillMaxWidth()
            .padding(top = paddingValues.calculateTopPadding()),
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = titleColor,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun KTopBarPreview() {
    KTopBar(
        modifier = Modifier.fillMaxWidth(),
        title = "Cities",
        paddingValues = PaddingValues(20.dp),
    )
}