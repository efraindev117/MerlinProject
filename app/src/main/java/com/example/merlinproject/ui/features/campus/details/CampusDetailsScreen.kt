package com.example.merlinproject.ui.features.campus.details

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.merlinproject.R
import com.example.merlinproject.domain.model.campus.uuuuuuu.CampusUniversityModel
import com.example.merlinproject.ui.theme.MerlinProjectIcons
import com.example.merlinproject.ui.theme.lexendFontFamily
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

// TODO: Optimizar la vista con las componibles... 

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CampusDetailsScreen(
    navController: NavController
) {

    val campus = remember {
        navController.previousBackStackEntry?.savedStateHandle?.get<CampusUniversityModel>("campus")
    }

    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(
                    text = campus!!.campusName,
                    fontFamily = lexendFontFamily,
                    fontWeight = FontWeight.Bold,
                )
            },
        )
    }
    ) { paddingValues ->
        ContentDetailsMapsScreen(
            paddingValues,
            campus!!.lng,
            campus.lat,
            campus.campusAddress,
            campus.campusPhone
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContentDetailsMapsScreen(
    paddingValues: PaddingValues,
    lng: Double,
    lat: Double,
    campusAddress: String,
    campusPhone: String
) {
    val uiSettings by remember {
        mutableStateOf(
            MapUiSettings(
                zoomControlsEnabled = true,
                zoomGesturesEnabled = true,
                mapToolbarEnabled = true,
                scrollGesturesEnabled = true,
                scrollGesturesEnabledDuringRotateOrZoom = true,
                tiltGesturesEnabled = false
            )
        )
    }
    val cameraPosition = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(lat, lng), 15f)
    }
    val context = LocalContext.current
    ConstraintLayout(
        modifier = Modifier
            .padding(paddingValues)
            .verticalScroll(rememberScrollState())
    ) {
        val (maps, card, contact, cardServices, academic) = createRefs()
        Box(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .height(250.dp)
                .constrainAs(maps) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .clip(RoundedCornerShape(16.dp))
                .background(Color.Gray), content = {
                GoogleMap(
                    Modifier,
                    uiSettings = uiSettings,
                    cameraPositionState = cameraPosition
                ) {
                    Marker(
                        state = MarkerState(position = LatLng(lat, lng))
                    )
                }
            }
        )

        Text(
            modifier = Modifier
                .padding(start = 16.dp)
                .constrainAs(contact) {
                    top.linkTo(maps.bottom)
                    start.linkTo(parent.start)
                    bottom.linkTo(card.top)
                },
            text = "Contacto",
            fontFamily = lexendFontFamily,
            fontWeight = FontWeight.SemiBold,
        )

        ElevatedCard(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 8.dp)
                .fillMaxWidth()
                .constrainAs(card) {
                    top.linkTo(contact.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        ) {
            Column(modifier = Modifier, horizontalAlignment = Alignment.CenterHorizontally,
                content = {
                    Row(modifier = Modifier.padding(8.dp), // Ajusta el espacio según tus necesidades
                        verticalAlignment = Alignment.CenterVertically, content = {
                            Icon(
                                modifier = Modifier
                                    .width(30.dp)
                                    .height(30.dp),
                                imageVector = MerlinProjectIcons.MapsCampusIcon,
                                tint = MaterialTheme.colorScheme.tertiary,
                                contentDescription = stringResource(
                                    id = R.string.icons_default_description
                                )
                            )

                            Text(
                                text = campusAddress,
                                modifier = Modifier.padding(8.dp),
                                textAlign = TextAlign.Start,
                                fontFamily = lexendFontFamily,
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.Normal,
                            )
                        }
                    )
                    Row(
                        modifier = Modifier.padding(8.dp),
                        verticalAlignment = Alignment.Bottom
                    ) {
                        OutlinedButton(
                            onClick = {
                                context.sendMail(to = "example@gmail.com", subject = "Some subject")
                            },
                            modifier = Modifier.weight(1f)
                        ) {
                            Icon(
                                imageVector = MerlinProjectIcons.EmailCampusIcon,
                                contentDescription = stringResource(id = R.string.icons_default_description)
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            Text(
                                text = "Correo",
                                fontFamily = lexendFontFamily,
                                fontWeight = FontWeight.Normal,
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Button(
                            onClick = {
                                if (campusPhone.isNotEmpty()) {
                                    val dialIntent = Intent(
                                        Intent.ACTION_DIAL,
                                        Uri.parse("tel:$campusPhone".trim())
                                    )
                                    context.startActivity(dialIntent)
                                } else {
                                    Toast.makeText(
                                        context,
                                        "No hay número de teléfono disponible",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            },
                            modifier = Modifier.weight(1f)
                        ) {
                            Icon(
                                imageVector = MerlinProjectIcons.PhoneCampusIcon,
                                contentDescription = stringResource(id = R.string.icons_default_description)
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            Text(
                                text = "Llamar",
                                fontFamily = lexendFontFamily,
                                fontWeight = FontWeight.Normal,
                            )
                        }
                    }
                })
        }
        var expanded by remember { mutableStateOf (false) }
        ElevatedCard(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 8.dp)
                .fillMaxWidth()
                .constrainAs(cardServices) {
                    top.linkTo(card.bottom)
                    start.linkTo(parent.start)
                },
        ) {
            Column(modifier = Modifier,
                content = {
                    Row(verticalAlignment = Alignment.CenterVertically, content = {
                        Text(
                            modifier = Modifier
                                .padding(8.dp)
                                .weight(1f),
                            text = "Servicios adicionales",
                            fontFamily = lexendFontFamily,
                            fontWeight = FontWeight.SemiBold
                        )
                        IconButton(
                            modifier = Modifier.padding(8.dp),
                            onClick = {
                                expanded = !expanded
                            }) {    
                            Icon(
                                imageVector = MerlinProjectIcons.ExpandMoreCampusIcon,
                                contentDescription = stringResource(id = R.string.icons_default_description)
                            )
                        }
                    })
                }
            )
            if (expanded) {
                Text(
                    text = "Content Sample for Display on Expansion of Card",
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}

fun Context.sendMail(to: String, subject: String) {
    try {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "vnd.android.cursor.item/email" // or "message/rfc822"
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(to))
        intent.putExtra(Intent.EXTRA_SUBJECT, subject)
        startActivity(intent)
    } catch (e: ActivityNotFoundException) {
        Toast.makeText(
            this,
            "No se encontró una aplicación de correo electrónico",
            Toast.LENGTH_SHORT
        ).show()
    } catch (e: SecurityException) {
        Toast.makeText(
            this,
            "No se tienen los permisos necesarios para enviar correos electrónicos",
            Toast.LENGTH_SHORT
        ).show()
    } catch (t: Throwable) {
        Toast.makeText(
            this,
            "Se produjo un error al intentar enviar el correo electrónico",
            Toast.LENGTH_SHORT
        ).show()
    }
}
