package com.example.merlinproject.ui.features.bachelors.composables

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.merlinproject.domain.model.campus.DocumentModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NestedFilterChipGroupFirebase(
    items: DocumentModel,
    defaultSelectedItemIndex: Int = 0,
    selectedItemIcon: ImageVector = Icons.Default.Done,
    onSelectedChanged: (Int) -> Unit = {},
    onClickCampus: (Int) -> Unit = {}
) {
    var selectedItemIndex by remember { mutableIntStateOf(defaultSelectedItemIndex) }
    var expanded by remember { mutableStateOf(true) }
    val alpha: Float by animateFloatAsState(if (expanded) 1f else 0.5f, label = "")


    LazyColumn(userScrollEnabled = false) {
        items(items.studios_list.size) { index: Int ->
            FilterChip(
                modifier = Modifier.padding(end = 6.dp),
                selected = selectedItemIndex == index,
                onClick = {
                    selectedItemIndex = index
                    onSelectedChanged(index)
                    onClickCampus(index)
                },
                label = { Text(items.studios_list[index].academy) },
                leadingIcon = if (selectedItemIndex == items.studios_list.size && expanded) {
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