package com.example.merlinproject.ui.features.bachelors

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.merlinproject.domain.model.CampusModel

@Composable
fun ColumnCampus(
    campus: List<CampusModel>,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(items = campus) { campus ->
            Text(text = campus.name)
        }
    }
}