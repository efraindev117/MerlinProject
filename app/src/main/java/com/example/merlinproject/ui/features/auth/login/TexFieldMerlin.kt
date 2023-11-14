package com.example.merlinproject.ui.features.auth.login

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldMerlin(
    modifier: Modifier = Modifier,
    value: String,
    keyboardOptions: KeyboardOptions,
    supportingText: (@Composable () -> Unit),
    label: (@Composable () -> Unit),
    placeholder: (@Composable () -> Unit),
    leadingIcon: (@Composable () -> Unit),
    trailingIcon: (@Composable () -> Unit),
    onValueChange: (String) -> Unit,
    onClickAction: () -> Unit,
) {
    TextField(
        modifier = modifier,
        value = value,
        colors = TextFieldDefaults.outlinedTextFieldColors(),
        supportingText = { supportingText() },
        onValueChange = { onValueChange("") },
        label = { label() },
        keyboardOptions = keyboardOptions,
        shape = MaterialTheme.shapes.extraSmall,
        leadingIcon = { leadingIcon() },
        trailingIcon = {
            trailingIcon()
        },

    )
}