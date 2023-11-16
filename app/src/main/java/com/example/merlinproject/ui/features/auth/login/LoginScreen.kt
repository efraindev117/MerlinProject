package com.example.merlinproject.ui.features.auth.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.merlinproject.R
import com.example.merlinproject.data.Resource
import com.example.merlinproject.ui.navigation.ScreensNavigation
import com.example.merlinproject.ui.theme.MerlinProjectIcons.cancelFilled
import com.example.merlinproject.ui.theme.MerlinProjectIcons.checkIconError
import com.example.merlinproject.ui.theme.MerlinProjectIcons.checkIconOk
import com.example.merlinproject.ui.theme.MerlinProjectIcons.emailOutlined
import com.example.merlinproject.ui.theme.MerlinProjectIcons.navigateToBack
import com.example.merlinproject.ui.theme.MerlinProjectIcons.passwordFilled
import com.example.merlinproject.ui.theme.MerlinProjectIcons.passwordOutlined
import com.example.merlinproject.ui.theme.MerlinProjectIcons.visibilityFilled
import com.example.merlinproject.ui.theme.MerlinProjectIcons.visibilityOffFilled
import com.example.merlinproject.ui.utils.composables.MerlinCircleProgressBar

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
        ScreenContent(modifier, innerPadding, navHostController, mviewModel)
    }
}

@Composable
fun ScreenContent(
    modifier: Modifier,
    innerPadding: PaddingValues,
    navHostController: NavHostController,
    mviewModel: LoginViewModel
) {
    val loginFlow = mviewModel.loginFlow.collectAsState()
    ConstraintLayout(
        modifier = modifier
            .fillMaxSize()
            .padding(innerPadding)
    ) {
        val (textFieldEmailAndPassword,
            textFieldPassword,
            buttonLogin,
            txtSocialNetwork,
            progressBarIndicator,
            btnTextRegister,
            btnGoogleSignIn) = createRefs()
        val middleGuideline = createGuidelineFromTop(.2f)
        val context = LocalContext.current
        //Email and Password
        // var email by remember { mutableStateOf("") }
        //Password
        // var password by remember { mutableStateOf("") }
        var passwordVisibility by remember { mutableStateOf(false) }
        //var isErrorState by remember { mutableStateOf(true) }
        //var loading by remember { mutableStateOf(false) }


        TextFieldMerlin(
            value = mviewModel.email.value.trim(),
            supportingText = {
                if (mviewModel.email.value.isNotEmpty()) {
                    val message = if (mviewModel.isEmailValid.value) {
                        mviewModel.emailMsgResult.value
                    } else {
                        mviewModel.emailMsgResult.value
                    }
                    Text(text = message)
                } else {
                    Text(text = "Correo electronico")
                }
            },
            label = { Text(text = "Email") },
            placeholder = { Text(text = "Email") },
            leadingIcon = { Icon(imageVector = emailOutlined, contentDescription = null) },
            trailingIcon = {
                val icon = if (passwordVisibility) {
                    cancelFilled
                } else {
                    cancelFilled
                }
                IconButton(
                    onClick = { mviewModel.email.value = "" },
                    enabled = mviewModel.email.value.isNotEmpty()
                ) {
                    Icon(imageVector = icon, contentDescription = null)
                }
            },
            onValueChange = { mviewModel.email.value = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            validateField = { mviewModel.validateEmail() },
            visualTransformation = VisualTransformation.None,
            modifier = Modifier.constrainAs(textFieldEmailAndPassword) {
                top.linkTo(middleGuideline)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )

        //Password
        TextFieldMerlin(
            value = mviewModel.password.value.trim(),
            supportingText = {
                if (mviewModel.password.value.isNotEmpty()) {
                    val message = if (mviewModel.isPasswordValid.value) {
                        mviewModel.passwordMsgResult.value
                    } else {
                        mviewModel.passwordMsgResult.value
                    }
                    Text(text = message)
                } else {
                    Text(text = "Contraseña")
                }
            },
            label = { Text(text = "password") },
            isError = mviewModel.isPasswordValid.value,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            placeholder = { Text(text = "") },
            leadingIcon = { Icon(imageVector = passwordFilled, contentDescription = null) },
            trailingIcon = {
                val icon = if (passwordVisibility) {
                    visibilityFilled
                } else {
                    visibilityOffFilled
                }
                IconButton(
                    onClick = { passwordVisibility = !passwordVisibility },
                    enabled = mviewModel.password.value.isNotEmpty()
                ) {
                    Icon(imageVector = icon, contentDescription = null)
                }
            },
            visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            onValueChange = { mviewModel.password.value = it },
            validateField = { mviewModel.validatePassword() },
            modifier = Modifier
                .padding(top = 32.dp)
                .constrainAs(textFieldPassword) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(textFieldEmailAndPassword.bottom)
                }
        )

        //Boton para mandar la peticion
        Button(
            onClick = { mviewModel.loginFirebase() },
            shape = MaterialTheme.shapes.extraSmall,
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.White,
                containerColor = Color.Black
            ),
            enabled = mviewModel.isEmailValid.value && mviewModel.isPasswordValid.value,
            modifier = Modifier
                .padding(top = 32.dp)
                .width(220.dp)
                .constrainAs(buttonLogin) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(textFieldPassword.bottom)
                }
        ){
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
                color = Color.Black,
                fontStyle = FontStyle.Normal,
                textDecoration = TextDecoration.Underline
            )
        }

        loginFlow.value?.let {
            when (it) {
                is Resource.Failure -> {
                    // TODO: con este vamos a probar la peticion a firebase
                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                }

                Resource.Loading -> {
                    MerlinCircleProgressBar(
                        modifier = modifier.constrainAs(progressBarIndicator) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                    )
                }

                is Resource.Success -> {
                    Toast.makeText(context, "Bienvenido a Daily UACM", Toast.LENGTH_SHORT).show()
                   navHostController.navigate(ScreensNavigation.BachelorsScreen.route)
                }

                else -> {
                    Toast.makeText(context, "Error desconocido", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}

