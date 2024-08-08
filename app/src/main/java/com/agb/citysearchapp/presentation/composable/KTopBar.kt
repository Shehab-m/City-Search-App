package com.agb.citysearchapp.presentation.composable

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.agb.citysearchapp.R

@SuppressLint("UnrememberedMutableState")
@ExperimentalMaterial3Api
@Composable
fun KTopBar(
    modifier: Modifier = Modifier,
    title: String,
    paddingValues: PaddingValues,
    titleColor: Color = MaterialTheme.colorScheme.secondary,
    horizontalPadding: Dp = 20.dp,
    icon: Painter? = null,
    iconColor: Color = Color.Unspecified
) {
    Box(
        modifier = modifier.padding(vertical = 16.dp, horizontal = horizontalPadding).fillMaxWidth()
            .padding(top = paddingValues.calculateTopPadding()),
    ) {
        Row(
            modifier = Modifier.align(Alignment.Center),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = titleColor,
            )
            icon?.let {
                Icon(
                    modifier = Modifier.size(26.dp),
                    painter = icon,
                    contentDescription = "Globe icon",
                    tint = iconColor
                )
            }
        }
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