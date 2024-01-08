package com.example.merlinproject.ui.features.maps.composables

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.merlinproject.common.Resource
import com.example.merlinproject.ui.features.maps.MapsCampusViewModel

@Composable
fun GetCampusMaps(
    modifier: Modifier,
    mViewModel: MapsCampusViewModel = hiltViewModel(),
    navHostController: NavHostController
) {
    when (val campus = mViewModel.campusResponse) {
        is Resource.Failure -> {
            Toast.makeText(LocalContext.current, "Ocurrio un error", Toast.LENGTH_SHORT).show()
        }

        Resource.Loading -> {
            // TODO: Shimer efect
        }

        is Resource.Success -> {
           // CampusCard(items = campus.data)
        }

        null -> TODO()
    }
}