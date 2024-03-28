package com.example.merlinproject.ui.features.resume

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.merlinproject.ui.features.resume.compose.HeaderHello

@Composable
fun ResumeScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    paddingValues: PaddingValues,
    mViewModel: ResumeViewModel = hiltViewModel()
) {
    ConstraintLayout(
        modifier
            .padding(paddingValues)
            .padding(16.dp)
            .fillMaxSize()
    ) {
        val (helloName) = createRefs()
        val guidelineTop = createGuidelineFromTop(.05f)

        HeaderHello(
            userName = mViewModel.userData.username,
            modifier = Modifier.constrainAs(helloName) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        })
    }
}

@Preview
@Composable
fun preResumen() {
    ResumeScreen(navController = rememberNavController(), paddingValues = PaddingValues())
}