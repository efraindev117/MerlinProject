package com.example.merlinproject.ui.features.bachelors

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.merlinproject.common.Resource

@Composable
fun GetOfertaAcademica(mViewModel: BachelorsViewModel = hiltViewModel()) {
    when (val campus = mViewModel.ofertaResponse) {
        is Resource.Failure -> {
            Toast.makeText(LocalContext.current, "ocurrio un error", Toast.LENGTH_SHORT).show()
        }
        Resource.Loading -> {
            // TODO: poner un progrees bar
        }
        is Resource.Success -> {

        }
        null -> TODO()
    }
}