package com.example.merlinproject.ui.features.auth.login

import TextFieldMerlin
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Key
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
import com.example.merlinproject.common.Resource
import com.example.merlinproject.ui.navigation.graph.AuthScreen
import com.example.merlinproject.ui.navigation.graph.Graph
import com.example.merlinproject.ui.theme.MerlinProjectIcons
import com.example.merlinproject.ui.theme.MerlinProjectIcons.cancelFilled
import com.example.merlinproject.ui.theme.MerlinProjectIcons.navigateToBack
import com.example.merlinproject.ui.theme.MerlinProjectIcons.visibilityFilled
import com.example.merlinproject.ui.theme.MerlinProjectIcons.visibilityOffFilled
import com.example.merlinproject.ui.theme.lexendFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navHostController: NavHostController,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.login_screen_title),
                        fontFamily = lexendFontFamily,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navHostController.navigate(AuthScreen.WelcomeScreen.route) }) {
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
    navHostController: NavHostController,
    mViewModel: LoginViewModel = hiltViewModel()
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
        var passwordVisibility by remember { mutableStateOf(false) }

        //Email
        TextFieldMerlin(
            modifier = Modifier
                .padding(16.dp)
                .constrainAs(textFieldEmailAndPassword) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(middleGuideline)
                },
            value = mViewModel.email.value,
            validateField = { mViewModel.validateEmail() },
            label = stringResource(id = R.string.login_screen_email_text_field_label),
            supportingText = {
                if (mViewModel.email.value.isNotEmpty()) {
                    val message = if (mViewModel.emailValidate.value) {
                        mViewModel.emailErrMsg.value
                    } else {
                        mViewModel.emailErrMsg.value
                    }
                    Text(
                        text = message,
                        fontFamily = lexendFontFamily,
                        fontWeight = FontWeight.Light
                    )
                } else {
                    Text(
                        text = stringResource(id = R.string.login_screen_email_text_field_msg),
                        fontFamily = lexendFontFamily,
                        fontWeight = FontWeight.Light
                    )
                }
            },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
            visualTransformation = VisualTransformation.None,
            leadingIcon = MerlinProjectIcons.emailOutlined,
            trailingIcon = {
                val icon = if (passwordVisibility) {
                    cancelFilled
                } else {
                    cancelFilled
                }
                IconButton(
                    onClick = { mViewModel.email.value = "" },
                    enabled = mViewModel.email.value.isNotEmpty()
                ) {
                    Icon(imageVector = icon, contentDescription = null)
                }
            },
            onValueChange = { mViewModel.email.value = it }
        )

        //Password
        TextFieldMerlin(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .constrainAs(textFieldPassword) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(textFieldEmailAndPassword.bottom)
                },
            value = mViewModel.password.value,
            validateField = { mViewModel.validatePassword() },
            label = stringResource(id = R.string.login_screen_password_text_field_label),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
            visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            leadingIcon = Icons.Default.Key,
            onValueChange = {
                mViewModel.password.value = it
                //Lógica de validación específica del email
            },
            trailingIcon = {
                val icon = if (passwordVisibility) {
                    visibilityFilled
                } else {
                    visibilityOffFilled
                }
                IconButton(
                    onClick = { passwordVisibility = !passwordVisibility },
                    enabled = mViewModel.password.value.isNotEmpty()
                ) {
                    Icon(imageVector = icon, contentDescription = null)
                }
            },
            supportingText = {
                if (mViewModel.password.value.isNotEmpty()) {
                    val message = if (mViewModel.passwordValidate.value) {
                        mViewModel.passwordErrMsg.value
                    } else {
                        mViewModel.passwordErrMsg.value
                    }
                    Text(
                        text = message, fontFamily = lexendFontFamily,
                        fontWeight = FontWeight.Light
                    )
                } else {
                    Text(
                        text = stringResource(id = R.string.login_screen_password_text_field_label),
                        fontFamily = lexendFontFamily,
                        fontWeight = FontWeight.Light
                    )
                }
            },
        )

        //Boton para mandar la peticion
        Button(
            onClick = {
                mViewModel.firebaseLoginWithEmailAndPassword()
            },
            enabled = mViewModel.isEnableFirebaseLoginButton,
            shape = MaterialTheme.shapes.extraSmall,
            modifier = Modifier
                .padding(top = 32.dp)
                .width(220.dp)
                .constrainAs(buttonLogin) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(textFieldPassword.bottom)
                }
        ) {
            Text(
                text = stringResource(id = R.string.login_screen_title_button),
                fontFamily = lexendFontFamily,
                fontWeight = FontWeight.SemiBold
            )
        }

        Text(
            text = stringResource(id = R.string.login_screen_social_txt_ing),
            fontFamily = lexendFontFamily,
            fontWeight = FontWeight.Normal,
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
                text = stringResource(id = R.string.login_screen_title_Google_button),
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        TextButton(
            onClick = { navHostController.navigate(AuthScreen.RegisterScreen.route) },
            modifier.constrainAs(btnTextRegister) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            }) {
            Text(
                text = stringResource(id = R.string.login_screen_title_text_button),
                fontFamily = lexendFontFamily,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black,
                fontStyle = FontStyle.Normal,
                textDecoration = TextDecoration.Underline
            )
        }
    }
    mViewModel.loginFlow.collectAsState().value?.let { resourceState ->
        when (resourceState) {
            Resource.Loading -> {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    CircularProgressIndicator()
                }
            }

            is Resource.Success -> {
                LaunchedEffect(Unit) {
                    navHostController.popBackStack()
                    navHostController.navigate(route = Graph.HOME) {
                        //Graph.HOME
                        //Eliminar el screen anterior
                        popUpTo(Graph.AUTH) { inclusive = true }
                    }
                }
            }
            is Resource.Failure -> {
                Toast.makeText(LocalContext.current, resourceState.toString(), Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}
