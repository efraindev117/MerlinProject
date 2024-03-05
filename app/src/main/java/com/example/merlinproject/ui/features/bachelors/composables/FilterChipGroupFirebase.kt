package com.example.merlinproject.ui.features.bachelors.composables

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.merlinproject.domain.model.campus.CampusModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterChipGroupFirebase(
    items: List<CampusModel>,
    defaultSelectedItemIndex: Int = 101,
    selectedItemIcon: ImageVector = Icons.Default.Done,
    onSelectedChanged: (Int) -> Unit = {},
    onClickCampus: (Int) -> Unit = {}
) {
    var selectedItemIndex by remember { mutableIntStateOf(defaultSelectedItemIndex) }
    var expanded by remember { mutableStateOf(true) }
    val alpha: Float by animateFloatAsState(if (expanded) 1f else 0.5f, label = "")
    //items(items.size) { index: Int ->
    LazyColumn {
        items(items = items.chunked(2)) { rowOfChips ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                rowOfChips.forEach { data ->
                    FilterChip(
                        modifier = Modifier
                            .graphicsLayer(alpha = alpha)
                            .padding(start = 6.dp),
                        selected =  selectedItemIndex == data.id,
                        onClick = {
                            selectedItemIndex = data.id
                            onSelectedChanged(data.id)
                            onClickCampus(data.id)
                        },
                        label = {
                            Text(
                                text = data.name,
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 1,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        },
                        leadingIcon = if (selectedItemIndex == data.id && expanded) {
                            {
                                Icon(
                                    imageVector = selectedItemIcon,
                                    contentDescription = "Localized Description",
                                    modifier = Modifier.size(FilterChipDefaults.IconSize)
                                )
                            }
                        } else {
                            null
                        }
                    )
                }
            }
        }
    }
}

