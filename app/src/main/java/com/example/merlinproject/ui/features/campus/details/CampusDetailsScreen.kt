package com.example.merlinproject.ui.features.campus.details

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.merlinproject.domain.model.campus.uuuuuuu.CampusUniversityModel
import com.example.merlinproject.ui.theme.lexendFontFamily

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
            campus.campusPhone,
            campus.campusEmail,
            campus.campusServices
        )
    }
}

@Composable
fun ContentDetailsMapsScreen(
    paddingValues: PaddingValues,
    lng: Double,
    lat: Double,
    campusAddress: String,
    campusPhone: String,
    campusEmail: String,
    campusServices: List<String>,
) {

    val context = LocalContext.current
    ConstraintLayout(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        val (boxGoogleMaps,
            cardCampusContact,
            cardMoreServices) = createRefs()

        BoxGoogleMaps(modifier = Modifier.constrainAs(boxGoogleMaps) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }, lat, lng)

        CardContactCampus(modifier = Modifier.constrainAs(cardCampusContact) {
            top.linkTo(boxGoogleMaps.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }, campusAddress,
            clickEmail = {
                context.sendMail(
                    to = campusEmail,
                    subject = "DailyUACM sin asunto"
                )
            },
            clickPhone = {
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
            })

        CardCampusServices(modifier = Modifier.constrainAs(cardMoreServices) {
            top.linkTo(cardCampusContact.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        },campusServices)

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
