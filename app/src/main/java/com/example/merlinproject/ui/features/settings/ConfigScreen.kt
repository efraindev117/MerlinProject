package com.example.merlinproject.ui.features.settings

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.merlinproject.R
import com.example.merlinproject.ui.navigation.composable.SettingsMenu
import com.example.merlinproject.ui.theme.MerlinProjectIcons
import com.example.merlinproject.ui.theme.lexendFontFamily
import com.example.merlinproject.ui.utils.composables.AlertDialogDesign
import com.example.merlinproject.ui.utils.composables.ModalSheetDesign
import com.google.android.gms.dynamic.IFragmentWrapper
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfigScreen(
    navController: NavController,
    paddingValues: PaddingValues
) {
    //SHEET
    var showSheet by remember { mutableStateOf(false) }

    if (showSheet) {
        ModalSheetDesign() {
            showSheet = false
        }
    }

    //DIALOG
    var showDialog by remember { mutableStateOf(false) }
    val list = SettingsMenu().settingsNavigationItems()
    if (showDialog) {
        AlertDialogDesign(
            onConfirmation = {
                showDialog = false
                println("Confirmation registered") // Aquí puedes agregar la lógica para manejar la confirmación
            },
            dialogTitle = stringResource(id = R.string.dialog_diversity_title),
            dialogText = stringResource(id = R.string.dialog_diversity_body),
            icon = MerlinProjectIcons.DiversitySettingsIcon
        )
    }

    ConstraintLayout(
        modifier = Modifier.padding(paddingValues)
            .background(MaterialTheme.colorScheme.surface)
            .fillMaxSize()
    ) {
        val menuSettingsColumn = createRef()
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(menuSettingsColumn) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
        ) {
            itemsIndexed(list) { index, menu ->
                CardItem(
                    title = menu.title ?: "",
                    icon = menu.selectedIcon,
                    onclick = {
                        if (index == 2) {
                            showDialog = true
                        }
                        if (index == 8) {
                            showSheet = true
                        }
                    }
                )
            }
        }
    }
}


@Composable
fun CardItem(
    title: String, icon: ImageVector,
    onclick: () -> Unit
) {
    Card(modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface),
        shape = MaterialTheme.shapes.extraSmall,
        onClick = { onclick() }) {
        Row(modifier = Modifier
            .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            content = {
                Icon(
                    imageVector = icon,
                    tint = MaterialTheme.colorScheme.tertiary,
                    contentDescription = ""
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = title,
                    fontFamily = lexendFontFamily,
                    fontWeight = FontWeight.Normal,
                )
            }
        )
    }
}