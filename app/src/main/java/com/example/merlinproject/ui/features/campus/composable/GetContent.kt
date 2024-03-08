package com.example.merlinproject.ui.features.campus.composable

import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.merlinproject.domain.model.campus.uuuuuuu.CampusUniversityModel
import com.example.merlinproject.ui.features.campus.CampusViewModel
import com.example.merlinproject.ui.navigation.graph.DetailsScreen
import com.example.merlinproject.ui.navigation.graph.Graph
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun GetContent(
    modifier: Modifier = Modifier,
    campus: List<CampusUniversityModel>,
    navController: NavController,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
    ) {
        items(items = campus, key = { campus -> campus.lazyId }) { campus ->
            CardCampusItem(
                campusName = campus.campusName,
                campusBody = campus.campusAddress,
                phoneCampus = campus.campusPhone,
                goToDetails = {
                    val campusPar = CampusUniversityModel(
                        campusName = campus.campusName,
                        lat = campus.lat,
                        lng = campus.lng,
                        campusAddress = campus.campusAddress,
                        campusPhone = campus.campusPhone,
                        campusEmail = campus.campusEmail,
                        campusServices = campus.campusServices

                    )
                    Log.d("campusServices", campus.campusServices.toString())
                    navController.currentBackStackEntry?.savedStateHandle?.set(
                        key = "campus",
                        value = campusPar
                    )
                    navController.navigate(Graph.DETAILS)
                }
            )
        }
    }
}
