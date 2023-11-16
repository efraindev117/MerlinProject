package com.example.merlinproject.ui.features.auth.login

import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldMerlin(
    modifier: Modifier = Modifier.width(300.dp),
    value: String,
    maxLines: Int = 1,
    singleLine: Boolean = true,
    isError: Boolean = false,
    validateField: () -> Unit = {},
    keyboardOptions: KeyboardOptions,
    supportingText: (@Composable () -> Unit),
    label: (@Composable () -> Unit),
    placeholder: (@Composable () -> Unit),
    leadingIcon: (@Composable () -> Unit),
    trailingIcon: (@Composable () -> Unit),
    onValueChange: (value: String) -> Unit,
    visualTransformation: VisualTransformation
) {
    TextField(
        modifier = modifier.width(280.dp),
        value = value,
        singleLine = singleLine,
        maxLines = maxLines,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = Color.Gray,
            focusedBorderColor = Color.Black,
            unfocusedBorderColor = Color.LightGray,
            unfocusedLeadingIconColor = Color.Black,
            focusedLeadingIconColor = Color.Black,
            disabledLabelColor = Color.Black,
            focusedLabelColor = Color.Black,
            errorLabelColor = Color.Red
        ),
        supportingText = { supportingText() },
        onValueChange = {
            onValueChange(it)
            validateField()
        },
        visualTransformation = visualTransformation,
        label = { label() },
        keyboardOptions = keyboardOptions,
        shape = MaterialTheme.shapes.extraSmall,
        leadingIcon = { leadingIcon() },
        trailingIcon = {
            trailingIcon()
        }
    )
}