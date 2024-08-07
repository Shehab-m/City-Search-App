package com.agb.citysearchapp.presentation.composable

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun KFilledButton(
    modifier: Modifier = Modifier,
    label: String,
    onClick: () -> Unit,
    contentColor: Color = MaterialTheme.colorScheme.onPrimary,
    background: Color = MaterialTheme.colorScheme.primary,
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = background,
            contentColor = contentColor,
        )
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = contentColor
        )
    }
}

@Composable
@Preview
fun KFilledButtonPreview() {
    MaterialTheme {
        KFilledButton(label = "Try Again", onClick = {})
    }
}
