package com.example.merlinproject.ui.features.maps

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.android.gms.maps.model.CameraPosition
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MapsScreen(
    navHostController: NavHostController,
    mapsCampusViewModel: MapsCampusViewModel = hiltViewModel()
) {
    CampusMaps()
}

@Composable
fun CampusMaps() {
    val properties by remember {
        mutableStateOf(MapProperties(mapType = MapType.NORMAL))
    }

    val disabledZoomMaps by remember {
        mutableStateOf(MapUiSettings(zoomControlsEnabled = false))
    }

    val singapore = LatLng(19.4326, -99.1332)
    val uacmCuautepec = LatLng(19.5556583, -99.1451107)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(singapore, 10f)
        position = CameraPosition.fromLatLngZoom(uacmCuautepec, 10f)
    }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        properties = properties,
        uiSettings = disabledZoomMaps,
        cameraPositionState = cameraPositionState
    ) {
        Marker(
            state = MarkerState(position = singapore),
            title = "Uacm campus Mexico",
            snippet = "uacm subtitulo"
        )
        Marker(
            state = MarkerState(position = uacmCuautepec),
            title = "UACM",
            snippet = "Plantel Cuautepec"
        )
    }
    // TODO: crear marcas para cada diferente plantel de la uacm
}