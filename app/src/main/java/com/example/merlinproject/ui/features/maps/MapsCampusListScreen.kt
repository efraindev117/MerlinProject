package com.example.merlinproject.ui.features.maps

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintLayoutScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.merlinproject.domain.model.campus.CampusModel
import com.example.merlinproject.ui.features.bachelors.firestore.GetCampus
import com.example.merlinproject.ui.features.maps.composables.GetCampusMaps

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapsCampusListScreen(
    navHostController: NavHostController,
    mapsCampusViewModel: MapsCampusViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(title = { Text(text = "UACM Campus") })
        }
    ) { paddingValues ->
        MapsCampusListScreenContent(modifier, paddingValues)
    }
}

@Composable
fun MapsCampusListScreenContent(
    modifier: Modifier,
    paddingValues: PaddingValues,

    ) {
}

@Preview
@Composable
fun composePreview() {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val guidelineTop = createGuidelineFromTop(.1f)
        val guidelineMedium = createGuidelineFromTop(.3f)
        val guidelineEnd = createGuidelineFromTop(.5f)
    }
}
