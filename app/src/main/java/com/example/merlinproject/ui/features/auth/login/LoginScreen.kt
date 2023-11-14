package com.example.merlinproject.ui.features.auth.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.merlinproject.R
import com.example.merlinproject.ui.navigation.ScreensNavigation
import com.example.merlinproject.ui.theme.MerlinProjectIcons.cancelFilled
import com.example.merlinproject.ui.theme.MerlinProjectIcons.emailOutlined
import com.example.merlinproject.ui.theme.MerlinProjectIcons.navigateToBack
import com.example.merlinproject.ui.theme.MerlinProjectIcons.passwordOutlined
import com.example.merlinproject.ui.theme.MerlinProjectIcons.visibilityFilled

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navHostController: NavHostController,
    modifier: Modifier = Modifier,
    mviewModel: LoginViewModel = hiltViewModel()
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text(text = "Inicia sesión") },
                navigationIcon = {
                    IconButton(onClick = { navHostController.navigate(ScreensNavigation.WelcomeScreen.route) }) {
                        Icon(imageVector = navigateToBack, contentDescription = "Navigate To Back")
                    }
                },
            )
        }
    ) { innerPadding ->
        ScreenContent(modifier, innerPadding, navHostController)
    }
}


@Composable
fun ScreenContent(
    modifier: Modifier,
    innerPadding: PaddingValues,
    navHostController: NavHostController
) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxSize()
            .padding(innerPadding)
    ) {
        val (textFieldEmailAndPassword,
            textFieldPassword,
            buttonLogin,
            txtSocialNetwork,
            btnTextRegister,
            btnGoogleSignIn) = createRefs()
        val middleGuideline = createGuidelineFromTop(.2f)
        val bottomGuideline = createGuidelineFromBottom(.2f)
        val context = LocalContext.current
        //Entry Text
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var loading by remember { mutableStateOf(false) }

        TextFieldMerlin(
            value = email,
            supportingText = { Text(text = "Correo electronico") },
            label = { Text(text = "Email", color = Color.Black) },
            placeholder = { Text(text = "Texto de placeHolder") },
            leadingIcon = { Icon(imageVector = emailOutlined, contentDescription = null) },
            trailingIcon = { Icon(imageVector = cancelFilled, contentDescription = null) },
            onValueChange = { email = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.constrainAs(textFieldEmailAndPassword) {
                top.linkTo(middleGuideline)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        ) {

        }
        TextFieldMerlin(
            value = password,
            supportingText = { Text(text = "Contraseña", color = Color.Black) },
            label = { Text(text = "password", color = Color.Black) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            placeholder = { Text(text = "") },
            leadingIcon = { Icon(imageVector = passwordOutlined, contentDescription = null) },
            trailingIcon = { Icon(imageVector = visibilityFilled, contentDescription = null) },
            onValueChange = { password = it },
            modifier = Modifier
                .padding(top = 32.dp)
                .constrainAs(textFieldPassword) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(textFieldEmailAndPassword.bottom)
                }
        ) {

        }
        Button(
            onClick = { navHostController.navigate(ScreensNavigation.BachelorsScreen.route)},
            shape = MaterialTheme.shapes.extraSmall,
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.White,
                containerColor = Color.Black
            ),
            modifier = Modifier
                .padding(top = 32.dp)
                .width(220.dp)
                .constrainAs(buttonLogin) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(textFieldPassword.bottom)
                }
        ) {
            Text(text = "Entrar")
        }


        Text(
            text = "Sign In with Social networks",
            style = MaterialTheme.typography.bodySmall,
            modifier = modifier.constrainAs(txtSocialNetwork) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(buttonLogin.bottom)
                bottom.linkTo(btnGoogleSignIn.top)
            })
        OutlinedButton(
            modifier = modifier
                .constrainAs(btnGoogleSignIn) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(txtSocialNetwork.bottom)
                    bottom.linkTo(btnTextRegister.top)
                },
            onClick = { })
        {
            Image(
                contentDescription = null,
                painter = painterResource(id = R.drawable.ic_google),
                modifier = Modifier
                    .padding(end = 8.dp)
                    .width(24.dp)
                    .height(24.dp)
            )
            Text(
                text = "Continue with google",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Normal, color = MaterialTheme.colorScheme.onSurface
            )
        }

        TextButton(
            onClick = { navHostController.navigate(ScreensNavigation.RegisterScreen.route) },
            modifier.constrainAs(btnTextRegister) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            }) {
            Text(
                text = "No tienes una cuenta? Registrate!",
                fontStyle = FontStyle.Normal,
                textDecoration = TextDecoration.Underline
            )
        }

    }
}

