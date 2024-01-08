package com.example.merlinproject.ui.features.bachelors

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.HistoryEdu
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.merlinproject.R
import com.example.merlinproject.ui.features.bachelors.firestore.GetCampus
import com.example.merlinproject.ui.features.bachelors.firestore.GetCampusDocument
import com.example.merlinproject.ui.theme.MerlinProjectIcons.labelImportantDefault
import kotlin.reflect.KProperty

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BachelorsScreen(
    navHostController: NavHostController,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(title = {
                Text(
                    text = stringResource(id = R.string.bachelors_screen_title),
                    style = MaterialTheme.typography.headlineSmall
                )
            })
        }
    ) { paddingValues ->
        ScreenBachelorsContent(modifier, paddingValues)
    }
}

@Composable
fun ScreenBachelorsContent(
    modifier: Modifier,
    paddingValues: PaddingValues,
) {
    ConstraintLayout(
        modifier
            .fillMaxSize()
            .padding(paddingValues)

    ) {
        val (lazyCampus,
            txtCampusQuestión,
            txtOferta,
            cardWithMessage) = createRefs()
        val guidelineTop = createGuidelineFromTop(.1f)
        val guidelineMedium = createGuidelineFromTop(.3f)
        val guidelineEnd = createGuidelineFromTop(.5f)

        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp)
                .constrainAs(cardWithMessage) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer)
        ) {
            Row(modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = labelImportantDefault, contentDescription = null)
                Text(
                    modifier = modifier.padding(start = 8.dp),
                    text = stringResource(id = R.string.bachelors_card_msg),
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }

        Column(modifier = modifier
            .padding(16.dp)
            .constrainAs(txtCampusQuestión) {
                top.linkTo(cardWithMessage.bottom)
                start.linkTo(parent.start)
                bottom.linkTo(lazyCampus.top)
            }
        ) {
            Row {
                Icon(imageVector = Icons.Default.LocationOn, contentDescription = null)
                Text(
                    text = "Plantel de inscripcion",
                    modifier.padding(start = 8.dp),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }
            GetCampus()
        }
        Column(
            modifier
                .padding(start = 16.dp)
                .constrainAs(txtOferta) {
                    start.linkTo(parent.start)
                    top.linkTo(txtCampusQuestión.bottom)
                }) {
            Row {
                Icon(imageVector = Icons.Default.HistoryEdu, contentDescription = null)
                Text(
                    text = "Academías",
                    modifier.padding(start = 8.dp),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }
            GetCampusDocument()
        }
        Box(modifier = modifier
            .constrainAs(lazyCampus) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(txtCampusQuestión.bottom)
            }) {
            // GetOfertaAcademica()
        }
    }
}
