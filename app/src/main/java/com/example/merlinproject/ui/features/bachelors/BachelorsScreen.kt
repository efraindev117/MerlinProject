package com.example.merlinproject.ui.features.bachelors


import android.graphics.Paint.Style
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.merlinproject.ui.theme.MerlinProjectIcons.academyIcon
import com.example.merlinproject.ui.theme.MerlinProjectIcons.assitOutlined
import com.example.merlinproject.ui.theme.MerlinProjectIcons.favoriteIcon
import com.example.merlinproject.ui.theme.MerlinProjectIcons.universityIcon


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BachelorsScreen(
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(title = { Text(text = "Ayudanos un poco") })
        }
    ) { paddingValues ->
        ScreenBachelorsContent(modifier, paddingValues)
    }
}

@Composable
fun ScreenBachelorsContent(modifier: Modifier, paddingValues: PaddingValues) {
    ConstraintLayout(
        modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        val (iconUniversity,
            txtUniversity,
            iconAcademy,
            txtAcademy,
            iconBachelors,
            txtBachelors) = createRefs()
        val guidelineTop = createGuidelineFromTop(.1f)
        val guidelineMedium = createGuidelineFromTop(.3f)
        val guidelineEnd = createGuidelineFromTop(.5f)

        Icon(
            imageVector = universityIcon,
            contentDescription = null,
            modifier
                .padding(start = 16.dp)
                .constrainAs(iconUniversity) {
                    top.linkTo(guidelineTop)
                    start.linkTo(parent.start)
                }
        )

        Text(
            text = "Completa tu Información",
            style = MaterialTheme.typography.bodyMedium,
            modifier = modifier
                .padding(start = 8.dp)
                .constrainAs(txtUniversity) {
                    top.linkTo(guidelineTop)
                    start.linkTo(iconUniversity.end)
                    bottom.linkTo(iconUniversity.bottom)
                }
        )

        Icon(
            imageVector = academyIcon,
            contentDescription = null,
            modifier
                .padding(start = 16.dp)
                .constrainAs(iconAcademy) {
                    top.linkTo(guidelineMedium)
                    start.linkTo(parent.start)
                }
        )

        Text(
            text = "Academia",
            style = MaterialTheme.typography.bodyMedium,
            modifier = modifier
                .padding(start = 8.dp)
                .constrainAs(txtAcademy) {
                    top.linkTo(guidelineMedium)
                    start.linkTo(iconAcademy.end)
                    bottom.linkTo(iconAcademy.bottom)
                }
        )

        Icon(
            imageVector = academyIcon,
            contentDescription = null,
            modifier
                .padding(start = 16.dp)
                .constrainAs(iconAcademy) {
                    top.linkTo(guidelineMedium)
                    start.linkTo(parent.start)
                }
        )

        Text(
            text = "Academia",
            style = MaterialTheme.typography.bodyMedium,
            modifier = modifier
                .padding(start = 8.dp)
                .constrainAs(txtAcademy) {
                    top.linkTo(guidelineMedium)
                    start.linkTo(iconAcademy.end)
                    bottom.linkTo(iconAcademy.bottom)
                }
        )

        Icon(
            imageVector = favoriteIcon,
            contentDescription = null,
            modifier
                .padding(start = 16.dp)
                .constrainAs(iconBachelors) {
                    top.linkTo(guidelineEnd)
                    start.linkTo(parent.start)
                }
        )

        Text(
            text = "Licenciatura",
            style = MaterialTheme.typography.bodyMedium,
            modifier = modifier
                .padding(start = 8.dp)
                .constrainAs(txtBachelors) {
                    top.linkTo(guidelineEnd)
                    start.linkTo(iconBachelors.end)
                    bottom.linkTo(iconBachelors.bottom)
                }
        )

    }


}

@Preview
@Composable
fun ProfilePReview(modifier: Modifier = Modifier) {
    BachelorsScreen(modifier)
}
