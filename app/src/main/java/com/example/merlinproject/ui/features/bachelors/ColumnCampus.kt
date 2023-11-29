package com.example.merlinproject.ui.features.bachelors

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SelectableChipColors
import androidx.compose.material3.SelectableChipElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.merlinproject.domain.model.campus.CampusModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColumnCampus(
    campus: List<CampusModel>,
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
    onSelectionChanged: (String) -> Unit = {}
) {
    var selected by remember { mutableStateOf(false) }
    var selectedChips by remember { mutableStateOf(setOf<String>()) }
    LazyColumn {
        items(items = campus.chunked(2)) { rowOfChips ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                rowOfChips.forEach { data ->
                    FilterChip(
                        modifier = Modifier.padding(start = 8.dp),
                        selected = selectedChips.contains(data.name),
                        onClick = { selectedChips = if (selectedChips.contains(data.name)) {
                            selectedChips - data.name
                        } else {
                            selectedChips + data.name
                        } },
                        label = {
                            Text(
                                text = data.name,
                                color = MaterialTheme.colorScheme.onSurface,
                                overflow = TextOverflow.Ellipsis
                            )
                        },
                        leadingIcon = if (selectedChips.contains(data.name)) {
                            {
                                Icon(
                                    imageVector = Icons.Filled.Done,
                                    contentDescription = "Done icon",
                                    modifier = Modifier.size(FilterChipDefaults.IconSize)
                                )
                            }
                        } else null
                    )
                }
            }
        }
    }
}