package com.example.merlinproject.ui.features.bachelors.firestore
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.merlinproject.common.Resource
import com.example.merlinproject.ui.features.bachelors.BachelorsViewModel
import com.example.merlinproject.ui.features.bachelors.composables.NestedFilterChipGroupFirebase


@Composable
fun GetCampusDocument(mViewModel: BachelorsViewModel = hiltViewModel()) {
    when (val campus = mViewModel.documentResponse) {
        is Resource.Failure -> {
            Toast.makeText(
                LocalContext.current, "No se encontró ese Plantel",
                Toast.LENGTH_SHORT
            ).show()
        }

        Resource.Loading -> {
            //TODO: Animacion de carga de información
        }

        is Resource.Success -> {
            NestedFilterChipGroupFirebase(
                items = campus.data!!)
        }

        null -> TODO()
    }
}