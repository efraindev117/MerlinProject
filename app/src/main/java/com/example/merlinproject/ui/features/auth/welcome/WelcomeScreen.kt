package com.example.merlinproject.ui.features.auth.welcome

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.example.merlinproject.ui.navigation.graph.AuthScreen

@Composable
fun WelcomeScreen(navHostController: NavHostController) {

    Surface {
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (buttonGoToLogin, buttonGoToRegister) = createRefs()
            val bottomGuide = createGuidelineFromBottom(.2f)
            Button(
                onClick = { navHostController.navigate(AuthScreen.LoginScreen.route) },
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
                onClick = { navHostController.navigate(AuthScreen.RegisterScreen.route) },
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
