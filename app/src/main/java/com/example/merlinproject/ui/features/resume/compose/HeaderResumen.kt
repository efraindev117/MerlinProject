package com.example.merlinproject.ui.features.resume.compose

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.example.merlinproject.R
import com.example.merlinproject.ui.theme.lexendFontFamily

@Composable
fun HeaderHello(
    userName:String = "",
    modifier: Modifier) {
    val infiniteTransition = rememberInfiniteTransition(label = "")
    val rotationAnimation = infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(tween(1000, easing = LinearEasing)), label = ""
    )
    val helloNameTxt = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                fontFamily = lexendFontFamily,
                fontWeight = FontWeight.SemiBold,
            )
        ) {
            append("!Hola, ")
        }
        withStyle(
            style = SpanStyle(
                fontFamily = lexendFontFamily,
                fontWeight = FontWeight.Light,
            )
        ) {
            append("${userName}!")
        }
    }
    val blueGradientBrush = Brush.linearGradient(
        colors = listOf(
            Color(0xFF007AFF), // Azul claro
            Color(0xFF00187A)  // Azul oscuro
        )
    )
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween // Añadir este modifier
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = helloNameTxt,
                style = MaterialTheme.typography.headlineMedium
            )
        }

        Image(
            modifier = Modifier.padding(end = 16.dp)
                .drawBehind {
                    rotate(rotationAnimation.value) {
                        drawCircle(blueGradientBrush, style = Stroke(12f))
                    }
                }
                .clip(CircleShape)
                .height(50.dp)
                .width(50.dp),
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = ""
        )
    }
}
