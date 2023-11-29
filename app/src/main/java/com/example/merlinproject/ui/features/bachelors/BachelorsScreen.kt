package com.example.merlinproject.ui.features.bachelors


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.merlinproject.R
import com.example.merlinproject.ui.theme.MerlinProjectIcons.academyIcon
import com.example.merlinproject.ui.theme.MerlinProjectIcons.favoriteIcon
import com.example.merlinproject.ui.theme.MerlinProjectIcons.labelImportantDefault
import com.example.merlinproject.ui.theme.MerlinProjectIcons.universityIcon


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BachelorsScreen(
    navHostController: NavHostController,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(title = { Text(text = stringResource(id = R.string.bachelors_screen_title)) })
        }
    ) { paddingValues ->
        ScreenBachelorsContent(modifier, paddingValues)
    }
}

@Composable
fun ScreenBachelorsContent(
    modifier: Modifier,
    paddingValues: PaddingValues,
    mViewModel: BachelorsViewModel = hiltViewModel()
) {
    ConstraintLayout(
        modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        val (lazyCampus,
            txtCampusQuestión,
            cardWithMessage) = createRefs()
        val guidelineTop = createGuidelineFromTop(.1f)
        val guidelineMedium = createGuidelineFromTop(.3f)
        val guidelineEnd = createGuidelineFromTop(.5f)


        Card(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp)
            .height(60.dp)
            .constrainAs(cardWithMessage) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }) {
            Row(modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = labelImportantDefault, contentDescription = null)
                Text(
                    modifier = modifier.padding(start = 8.dp),
                    text = stringResource(id = R.string.bachelors_card_msg),
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Column(modifier = modifier
            .padding(8.dp)
            .constrainAs(txtCampusQuestión) {
                top.linkTo(cardWithMessage.bottom)
                start.linkTo(parent.start)
                bottom.linkTo(lazyCampus.top)
            }
        ) {
            Row {
                Icon(imageVector = Icons.Default.LocationOn, contentDescription = "")
                Text(text = "Plantel de inscripción", modifier.padding(start = 8.dp))
            }
            GetCampus()
        }

        Box(modifier = modifier
            .constrainAs(lazyCampus) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(txtCampusQuestión.bottom)
            }) {

        }
    }
}
