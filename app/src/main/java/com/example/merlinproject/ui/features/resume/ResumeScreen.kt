package com.example.merlinproject.ui.features.resume

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.merlinproject.ui.features.resume.compose.HeaderHello

@Composable
fun ResumeScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    paddingValues: PaddingValues
) {
    ConstraintLayout(
        modifier
            .padding(paddingValues)
            .padding(16.dp)
            .fillMaxSize()
    ) {
        val (helloName, havNiceDay, imageProfile) = createRefs()
        val guidelineTop = createGuidelineFromTop(.05f)

        HeaderHello(modifier = Modifier.constrainAs(helloName){
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