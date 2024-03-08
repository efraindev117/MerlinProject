package com.example.merlinproject.ui.features.campus.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import com.example.merlinproject.R
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun BoxGoogleMaps(
    modifier: Modifier = Modifier,
    lat: Double = 0.0,
    lng: Double = 0.0,
) {
    val uiSettings by remember {
        mutableStateOf(
            MapUiSettings(
                zoomControlsEnabled = true,
                zoomGesturesEnabled = true,
                mapToolbarEnabled = true,
                scrollGesturesEnabled = true,
                scrollGesturesEnabledDuringRotateOrZoom = true,
                tiltGesturesEnabled = false
            )
        )
    }
    val zoom = 15f
    val cameraPosition = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(
            LatLng(lat, lng)
            ,zoom
        )
    }
    Box(
        modifier = modifier
            .padding(dimensionResource(id = R.dimen.padding_medium_material))
            .fillMaxWidth()
            .height(dimensionResource(id = R.dimen.box_maps_height))
            .clip(RoundedCornerShape(dimensionResource(id = R.dimen.box_maps_roundedCornerShape)))
            .background(Color.Gray), content = {
            GoogleMap(
                Modifier,
                uiSettings = uiSettings,
                cameraPositionState = cameraPosition
            ) {
                Marker(
                    state = MarkerState(position = LatLng(lat, lng))
                )
            }
        }
    )
}