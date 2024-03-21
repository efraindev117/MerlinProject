package com.example.merlinproject.ui.utils.composables

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.example.merlinproject.R
import com.example.merlinproject.ui.theme.MerlinProjectIcons
import com.example.merlinproject.ui.theme.lexendFontFamily

@Composable
fun AlertDialogDesign(
    onConfirmation: () -> Unit = {},
    dialogTitle: String,
    dialogText: String,
    icon: ImageVector = MerlinProjectIcons.DiversitySettingsIcon,
) {
    AlertDialog(
        icon = {
            Icon(
                icon,
                tint = MaterialTheme.colorScheme.tertiary,
                contentDescription =
                stringResource(id = R.string.icons_default_description)
            )
        },
        title = {
            Text(text = dialogTitle,
                fontFamily = lexendFontFamily,
                fontWeight = FontWeight.SemiBold,)
        },
        text = {
            Text(text = dialogText,
                fontFamily = lexendFontFamily,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Light,)
        },
        onDismissRequest = {

        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text(
                    stringResource(id = R.string.dialog_button_title),
                    fontFamily = lexendFontFamily,
                    fontWeight = FontWeight.Normal,
                )
            }
        }
    )
}