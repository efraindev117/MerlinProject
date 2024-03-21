package com.example.merlinproject.ui.utils.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.merlinproject.R
import com.example.merlinproject.ui.features.campus.details.sendMail
import com.example.merlinproject.ui.theme.lexendFontFamily


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalSheetDesign(
    onDismiss: () -> Unit = {},
) {
    val modalBottomSheetState = rememberModalBottomSheetState()
    val context = LocalContext.current
    @Suppress("DEPRECATION") val versionCode =
        context.packageManager.getPackageInfo(context.packageName, 0).versionCode
    val versionName =
        context.packageManager.getPackageInfo(context.packageName, 0).versionName
    val bugTextAndContact = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                fontFamily = lexendFontFamily,
                fontWeight = FontWeight.Normal,
            )
        ) {
            append(stringResource(id = R.string.modal_bug_report))
        }
        withStyle(
            style = SpanStyle(
                fontFamily = lexendFontFamily,
                fontWeight = FontWeight.SemiBold,
                color = Color.Blue,
                textDecoration = TextDecoration.Underline,
            )
        ) {
            append(" " + stringResource(id = R.string.modal_developer_mail))
        }
    }
    val developerName = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                fontFamily = lexendFontFamily,
                fontWeight = FontWeight.Normal,
            )
        ) {
            append(stringResource(id = R.string.modal_developer_title) + " ")
        }
        withStyle(
            style = SpanStyle(
                fontFamily = lexendFontFamily,
                fontWeight = FontWeight.SemiBold,
                color = Color.Gray
            )
        ) {
            append(stringResource(id = R.string.modal_developer_name))
        }
    }
    val titleAppVersionNameVersionCode = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                fontFamily = lexendFontFamily,
                fontWeight = FontWeight.SemiBold,
            )
        ) {
            append(stringResource(id = R.string.modal_app_name))
        }
        withStyle(
            style = SpanStyle(
                fontFamily = lexendFontFamily,
                fontWeight = FontWeight.SemiBold,
            )
        ) {
            append(" V$versionName ($versionCode)")
        }
    }
    ModalBottomSheet(
        modifier = Modifier
            .fillMaxWidth(),
        sheetMaxWidth = 400.dp,
        onDismissRequest = { onDismiss() },
        sheetState = modalBottomSheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() },
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .size(350.dp)
        ) {
            Column(modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_medium_material)), content = {

                Text(
                    text = stringResource(id = R.string.modal_title),
                    fontFamily = lexendFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                )
                Text(
                    text = titleAppVersionNameVersionCode,
                    modifier = Modifier.padding(top =  dimensionResource(id = R.dimen.padding_min_material)),
                    color = Color.Gray
                )
                Text(
                    text = stringResource(id = R.string.modal_app_body),
                    fontFamily = lexendFontFamily,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Start
                )
                Text(text = developerName, modifier = Modifier.padding(top = dimensionResource(id = R.dimen.padding_medium_material)))
                Text(
                    text = bugTextAndContact,
                    modifier = Modifier
                        .padding(top = dimensionResource(id = R.dimen.padding_medium_material), bottom =  dimensionResource(id = R.dimen.padding_max_material))
                        .clickable {
                            context.sendMail(
                                to = "efrain_dev@outlook.com",
                                subject = "DailyUACM Comentarios"
                            )
                        })
            })
        }
    }
}