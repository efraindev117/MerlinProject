package com.example.merlinproject.ui.features.help

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.merlinproject.R
import com.example.merlinproject.ui.features.campus.details.sendMail
import com.example.merlinproject.ui.theme.MerlinProjectIcons
import com.example.merlinproject.ui.theme.lexendFontFamily

@Composable
fun HelperScreen(
    navController: NavController,
    paddingValues: PaddingValues
) {
    val context = LocalContext.current
    ConstraintLayout(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
    ) {
        val (image, title, body, emailButton, whatsAppButton) = createRefs()
        val topGuideline = createGuidelineFromTop(0.1f)
        val bottomGuideline = createGuidelineFromBottom(.1f)
        Image(
            modifier = Modifier
                .height(dimensionResource(id = R.dimen.image_help_illustration))
                .width(dimensionResource(id = R.dimen.image_help_illustration))
                .constrainAs(image) {
                    top.linkTo(topGuideline)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            painter = painterResource(id = R.drawable.ilustration_help),
            contentDescription = stringResource(id = R.string.icons_default_description)
        )

        Text(text = stringResource(id = R.string.title_help),
            fontFamily = lexendFontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
            modifier = Modifier
                .padding(top = dimensionResource(id = R.dimen.padding_max_material))
                .constrainAs(title) {
                    top.linkTo(image.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )

        Text(text = stringResource(id = R.string.body_help),
            fontFamily = lexendFontFamily,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.tertiary,
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.padding_medium_material))
                .fillMaxWidth()
                .constrainAs(body) {
                    top.linkTo(title.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )

        Button(onClick = {
            context.sendMail(
                to = "efrain_dev@outlook.com",
                subject = "DailyUACM app Android"
            )
        },
            modifier = Modifier
                .constrainAs(emailButton) {
                    bottom.linkTo(bottomGuideline)
                    end.linkTo(parent.end)
                    start.linkTo(whatsAppButton.end)
                }) {
            Icon(
                modifier = Modifier.padding(end = dimensionResource(id = R.dimen.padding_min_material)),
                imageVector = MerlinProjectIcons.EmailCampusIcon,
                contentDescription = stringResource(
                    id = R.string.icons_default_description))
            Text(
                text = stringResource(id = R.string.email_button_text_help),
                fontFamily = lexendFontFamily,
                fontWeight = FontWeight.Normal,
            )
        }

        OutlinedButton(onClick = { /*TODO Abrir el whatsApp con mi nuermo*/ },
            modifier = Modifier
                .constrainAs(whatsAppButton) {
                    bottom.linkTo(bottomGuideline)
                    start.linkTo(parent.start)
                    end.linkTo(emailButton.start)
                }) {
            Icon(
                modifier = Modifier.padding(end = dimensionResource(id = R.dimen.padding_min_material)),
                imageVector = MerlinProjectIcons.MessageCampusIcon,
                contentDescription = stringResource(
                id = R.string.icons_default_description))
            Text(
                text = stringResource(id = R.string.chat_button_text_help),
                fontFamily = lexendFontFamily,
                fontWeight = FontWeight.Normal,
            )
        }
    }
}