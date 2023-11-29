package com.example.merlinproject.ui.features.bachelors

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.merlinproject.common.Resource

@Composable
fun GetCampus(
    mViewModel: BachelorsViewModel = hiltViewModel()
) {
    when (val response = mViewModel.campusResponse) {
        is Resource.Failure -> {
            Toast.makeText(LocalContext.current, "Ocurrio un error", Toast.LENGTH_SHORT).show()
        }

        Resource.Loading -> {
            // TODO: Progres bar
        }

        is Resource.Success -> {
            ColumnCampus(campus = response.data)
        }

        null -> TODO()
    }


}