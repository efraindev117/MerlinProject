package com.example.merlinproject.ui.features.campus.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import com.example.merlinproject.R
import com.example.merlinproject.ui.theme.lexendFontFamily
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardCampusItem(
    modifier: Modifier = Modifier,
    campusName: String,
    campusBody: String,
    phoneCampus: String,
    positionMaps: LatLng,
    cameraPosition: CameraPositionState,
    goToDetails: () -> Unit
) {
    var uiSettings by remember { mutableStateOf(MapUiSettings(
        zoomControlsEnabled = false,
        zoomGesturesEnabled = false,
        mapToolbarEnabled = false,
        scrollGesturesEnabled = false,
        scrollGesturesEnabledDuringRotateOrZoom = false,
        tiltGesturesEnabled = false
    )) }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.padding_medium_material)),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(dimensionResource(id = R.dimen.campus_card_elevation)),
        onClick = { goToDetails() }
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.padding_medium_material)),
            content = {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.width_min_material)))
                    Text(
                        text = campusName,
                        fontFamily = lexendFontFamily,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.tertiary
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = campusBody,
                        modifier = Modifier.weight(1f),
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.Light,
                    )
                    Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.width_min_material)))

                    GoogleMap(
                        modifier
                            .size(dimensionResource(id = R.dimen.map_size_circle))
                            .clip(CircleShape),
                        uiSettings = uiSettings,
                        cameraPositionState = cameraPosition

                    ) {
                        Marker(
                            state = MarkerState(position = positionMaps),
                            title = campusName
                        )
                    }
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Tel: $phoneCampus",
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.ExtraLight,
                    )
                }
            }
        )
    }
}

