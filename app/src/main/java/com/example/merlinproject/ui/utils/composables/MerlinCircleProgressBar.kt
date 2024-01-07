package com.example.merlinproject.ui.utils.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MerlinCircleProgressBar(modifier: Modifier) {
    LinearProgressIndicator(
        modifier = modifier.fillMaxWidth().height(4.dp),
        color = Color.Black
    )
}