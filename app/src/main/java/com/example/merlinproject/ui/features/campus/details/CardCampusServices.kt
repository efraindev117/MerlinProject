package com.example.merlinproject.ui.features.campus.details

import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.ChipElevation
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.merlinproject.R
import com.example.merlinproject.ui.theme.MerlinProjectIcons
import com.example.merlinproject.ui.theme.lexendFontFamily
import com.example.merlinproject.ui.utils.MerlinIcons

@Composable
fun CardCampusServices(
    modifier: Modifier = Modifier,
    services: List<String>
) {
    ElevatedCard(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Column(modifier = Modifier
            .fillMaxSize(),
            content = {
                Row(verticalAlignment = Alignment.CenterVertically, content = {
                    Text(
                        modifier = Modifier
                            .padding(dimensionResource(id = R.dimen.padding_min_material))
                            .weight(1f),
                        text = stringResource(id = R.string.card_title_more_services),
                        fontFamily = lexendFontFamily,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                )
                Row(verticalAlignment = Alignment.CenterVertically, content = {
                    Log.d("services", services.toString())
                    LazyRow() {
                        itemsIndexed(services) { index, service ->
                            CampusServiceItem(service = service, index = index)
                        }
                    }
                })
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CampusServiceItem(service: String, index: Int) {
    val icon = when (index) {
        0 -> MerlinProjectIcons.RestaurantCampusIcon
        1 -> MerlinProjectIcons.BooksCampusIcon
        2 -> MerlinProjectIcons.FestivalCampusIcon
        3 -> MerlinProjectIcons.CarCampusIcon
        4 -> MerlinProjectIcons.LabCampusIcon
        5 -> MerlinProjectIcons.MedicalCampusIcon
        6 -> MerlinProjectIcons.WifiCampusIcon
        else -> MerlinProjectIcons.academyIcon
    }
    AssistChip(
        modifier = Modifier.padding(8.dp),
        onClick = { },
        label = { Text(service) },
        leadingIcon = {
            Icon(
                modifier = Modifier.size(AssistChipDefaults.IconSize),
                imageVector = icon,
                contentDescription = stringResource(id = R.string.icons_default_description)
            )
        }
    )
}
