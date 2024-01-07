package com.example.merlinproject.ui.features.bachelors.firestore

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.merlinproject.common.Resource
import com.example.merlinproject.ui.features.bachelors.BachelorsViewModel
import com.example.merlinproject.ui.features.bachelors.composables.FilterChipGroupFirebase

@Composable
fun GetCampus(mViewModel: BachelorsViewModel = hiltViewModel()) {
    when (val campus = mViewModel.campusResponse) {
        is Resource.Failure -> {
            Toast.makeText(LocalContext.current, "Ocurrio un error", Toast.LENGTH_SHORT).show()
        }
        Resource.Loading -> {
            // TODO: Animacion de carga de información
        }

        is Resource.Success -> {
            // ColumnCampus(campus = campus.data)
            FilterChipGroupFirebase(
                items = campus.data,
                onClickCampus = {
                    when (it) {
                        101 -> {
                            mViewModel.getDocumentCuautepec()
                        }

                        102 -> {
                            mViewModel.getDocumentDelValle()
                        }

                        103 -> {
                            mViewModel.getDocumentSanLorenzo()
                        }

                        105 -> {
                            mViewModel.getDocumentCentro()
                        }

                        104 -> {
                            mViewModel.getDocumentCasaLibertad()
                        }
                    }
                }
            )
        }
        null -> TODO()
    }
}