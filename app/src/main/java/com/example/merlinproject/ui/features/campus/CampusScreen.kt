package com.example.merlinproject.ui.features.campus

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.merlinproject.common.Resource
import com.example.merlinproject.ui.features.campus.composable.GetContent

@Composable
fun CampusScreen(
    navController: NavController,
    paddingValues: PaddingValues,
    mViewModel: CampusViewModel = hiltViewModel(),
) {
    ConstraintLayout(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
    ) {
        val (items, progrssIndicator) = createRefs()
        val topGuideline = createGuidelineFromTop(0.01f)
        when (val campusResponse = mViewModel.campusResponse) {
            is Resource.Failure -> {
                Toast.makeText(LocalContext.current, "Ocurrio un error", Toast.LENGTH_SHORT).show()
            }

            is Resource.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                    CircularProgressIndicator()
                }
            }

            is Resource.Success -> {
                GetContent(modifier = Modifier.constrainAs(items) {
                    top.linkTo(topGuideline)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }, campus = campusResponse.data, navController = navController)
            }

            else -> {
                Resource.Loading
            }
        }
    }
}


@Preview
@Composable
fun CampusScreenPrev() {
}