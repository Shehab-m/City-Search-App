package com.agb.citysearchapp.presentation.composable

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.agb.citysearchapp.R
import com.agb.citysearchapp.ui.theme.dimens

/**
 * A customizable search field composable designed for enhanced UX.
 *
 * @param modifier Modifier to customize the appearance and layout.
 * @param onValueChange Lambda to handle text change events.
 * @param value Initial text value.
 * @param containerColor Background color of the text field.
 * @param borderColor Border color of the text field.
 * @param textColor Color of the text inside the field.
 * @param placeHolder Placeholder text for the field.
 * @param textStyle Style for the text inside the field.
 * @param singleLine Boolean to indicate if the field is single line.
 *
 * Designed for a responsive and visually appealing search input experience.
 */
@Composable
fun KSearchField(
    @SuppressLint("ModifierParameter")
    modifier: Modifier = Modifier.height(MaterialTheme.dimens.textFieldHeight),
    onValueChange: (String) -> Unit,
    value: String = "",
    containerColor: Color = MaterialTheme.colorScheme.tertiary,
    borderColor: Color = MaterialTheme.colorScheme.tertiary,
    textColor: Color = MaterialTheme.colorScheme.secondary,
    placeHolder: String = "Search",
    textStyle: TextStyle = MaterialTheme.typography.bodyLarge,
    singleLine: Boolean = true
) {
    var fieldText by remember(value) { mutableStateOf(value) }
    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        textStyle = textStyle.copy(fontWeight = FontWeight(400)),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = containerColor,
            focusedTextColor = textColor,
            focusedIndicatorColor = borderColor,
            unfocusedContainerColor = containerColor,
            unfocusedIndicatorColor = borderColor,
            unfocusedTextColor = textColor,
            cursorColor = textColor,
        ),
        shape = RoundedCornerShape(24.dp),
        value = fieldText,
        onValueChange = {
            fieldText = it
            onValueChange(it)
        },
        leadingIcon = {
            Icon(
                modifier = Modifier.size(36.dp),
                painter = painterResource(id = R.drawable.search_icon),
                contentDescription = "Search icon",
                tint = MaterialTheme.colorScheme.primary
            )
        },
        placeholder = {
            Text(
                text = placeHolder,
                color = MaterialTheme.colorScheme.onTertiary,
                style = MaterialTheme.typography.bodyLarge
            )
        },
        singleLine = singleLine
    )
}

@Preview
@Composable
fun KSearchFieldPreview() {
    KSearchField(
        value = "",
        onValueChange = {},
        placeHolder = "Search"
    )
}