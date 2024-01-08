package com.example.merlinproject.ui.features.auth.welcome

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.example.merlinproject.ui.navigation.ScreensNavigation

@Composable
fun WelcomeScreen(navHostController: NavHostController) {

    Surface {
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (buttonGoToLogin, buttonGoToRegister) = createRefs()
            val bottomGuide = createGuidelineFromBottom(.2f)
            Button(
                onClick = { navHostController.navigate(ScreensNavigation.LoginScreen.route) },
                shape = MaterialTheme.shapes.extraSmall,
                modifier = Modifier
                    .width(220.dp)
                    .constrainAs(buttonGoToLogin) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(bottomGuide)
                    }
            ) {
                Text(text = "Iniciar sesión")
            }

            OutlinedButton(
                onClick = { navHostController.navigate(ScreensNavigation.RegisterScreen.route) },
                shape = MaterialTheme.shapes.extraSmall,
                modifier = Modifier
                    .width(220.dp)
                    .padding(top = 8.dp)
                    .constrainAs(buttonGoToRegister) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(buttonGoToLogin.bottom)
                    }
            ) {
                Text(text = "Registrarme")
            }

        }
    }

}
