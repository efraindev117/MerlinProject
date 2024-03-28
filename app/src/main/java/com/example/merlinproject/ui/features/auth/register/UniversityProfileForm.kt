package com.example.merlinproject.ui.features.auth.register

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController

@Composable
fun UniversityProfileForm(
    navController: NavController,
) {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (title, boxForm) = createRefs()

        Text(text = "Uacm Form",
            modifier = Modifier
                .padding(16.dp)
                .constrainAs(title){
            top.linkTo(parent.top)
            start.linkTo(parent.start)
        })
    }
}