package com.example.merlinproject.ui.features.auth.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.merlinproject.R
import com.example.merlinproject.ui.features.auth.login.LoginViewModel
import com.example.merlinproject.ui.features.auth.login.TextFieldMerlin
import com.example.merlinproject.ui.navigation.ScreensNavigation
import com.example.merlinproject.ui.theme.MerlinProjectIcons
import com.example.merlinproject.ui.theme.MerlinProjectIcons.cancelOutlined

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    navHostController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier,
    mViewModel: RegisterViewModel = hiltViewModel()
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text(text = "Registro") },
                navigationIcon = {
                    IconButton(onClick = { navHostController.navigate(ScreensNavigation.WelcomeScreen.route) }) {
                        Icon(
                            imageVector = MerlinProjectIcons.navigateToBack,
                            contentDescription = "Navigate To Back"
                        )
                    }
                },
            )
        }
    ) { innerPadding ->
        ScreenRegisterContent(modifier, innerPadding, mViewModel, navHostController)
    }
}

@Composable
fun ScreenRegisterContent(
    modifier: Modifier,
    innerPadding: PaddingValues,
    mViewModel: RegisterViewModel,
    navHostController: NavHostController
) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxSize()
            .padding(innerPadding)
    ) {
        val (textFieldEmailAndPassword,
            texFieldUsername,
            textFieldPassword,
            buttonLogin,
            txtSocialNetwork,
            btnGoogleSignIn) = createRefs()
        val middleGuideline = createGuidelineFromTop(.1f)
        val bottomGuideline = createGuidelineFromBottom(.3f)
        var loading by remember { mutableStateOf(false) }
        val context = LocalContext.current
        //Email
        var email by remember { mutableStateOf("") }
        //password
        var password by remember { mutableStateOf("") }
        var passwordVisibility by remember { mutableStateOf(false) }
        var usernameVisibility by remember { mutableStateOf(false) }

        //Username
        TextFieldMerlin(
            value = mViewModel.username.value,
            validateField = { mViewModel.validateUsername() },
            supportingText = {
                if (mViewModel.username.value.isNotEmpty()) {
                    val message = if (mViewModel.isUsernameValid.value) {
                        mViewModel.usernameMsgResult.value
                    } else {
                        mViewModel.usernameMsgResult.value
                    }
                    Text(text = message)
                } else {
                    Text(text = "Nombre de usuario")
                }
            },
            label = { Text(text = "Nombre", color = Color.Black) },
            placeholder = { Text(text = "Texto de placeHolder") },
            leadingIcon = {
                Icon(
                    imageVector = MerlinProjectIcons.usernameIcon,
                    contentDescription = null
                )
            },
            trailingIcon = {
                val icon = if (usernameVisibility) {
                    MerlinProjectIcons.cancelOutlined
                } else {
                    MerlinProjectIcons.cancelOutlined
                }
                IconButton(
                    onClick = { mViewModel.username.value = "" },
                    enabled = mViewModel.username.value.isNotEmpty()
                ) {
                    Icon(imageVector = icon, contentDescription = null)
                }
            },
            isError = true,
            onValueChange = { mViewModel.username.value = it },
            visualTransformation = VisualTransformation.None,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier
                .constrainAs(texFieldUsername) {
                    top.linkTo(middleGuideline)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )

        //Email
        TextFieldMerlin(
            value = mViewModel.email.value,
            validateField = { mViewModel.validateEmail() },
            supportingText = {
                if (mViewModel.email.value.isNotEmpty()) {
                    val message = if (mViewModel.isEmailValid.value) {
                        mViewModel.emailMsgResult.value
                    } else {
                        mViewModel.emailMsgResult.value
                    }
                    Text(text = message)
                } else {
                    Text(text = "Correo electronico")
                }
            },
            label = { Text(text = "Email", color = Color.Black) },
            placeholder = { Text(text = "Texto de placeHolder") },
            leadingIcon = {
                Icon(
                    imageVector = MerlinProjectIcons.emailOutlined,
                    contentDescription = null
                )
            },
            trailingIcon = {
                val icon = if (passwordVisibility) {
                    MerlinProjectIcons.cancelOutlined
                } else {
                    MerlinProjectIcons.cancelOutlined
                }
                IconButton(
                    onClick = { mViewModel.email.value = "" },
                    enabled = mViewModel.email.value.isNotEmpty()
                ) {
                    Icon(imageVector = icon, contentDescription = null)
                }
            },
            isError = true,
            onValueChange = { mViewModel.email.value = it },
            visualTransformation = VisualTransformation.None,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier
                .padding(top = 16.dp)
                .constrainAs(textFieldEmailAndPassword) {
                    top.linkTo(texFieldUsername.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )

        //Password
        TextFieldMerlin(
            value = mViewModel.password.value,
            validateField = { mViewModel.validatePassword() },
            supportingText = {
                if (mViewModel.password.value.isNotEmpty()) {
                    val message = if (mViewModel.isPasswordValid.value) {
                        mViewModel.passwordMsgResult.value
                    } else {
                        mViewModel.passwordMsgResult.value
                    }
                    Text(text = message)
                } else {
                    Text(text = "Contraseña")
                }
            },
            label = { Text(text = "password", color = Color.Black) },
            placeholder = { Text(text = "") },
            visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            leadingIcon = {
                Icon(
                    imageVector = MerlinProjectIcons.passwordOutlined,
                    contentDescription = null
                )
            },

            trailingIcon = {
                val icon = if (passwordVisibility) {
                    MerlinProjectIcons.visibilityFilled
                } else {
                    MerlinProjectIcons.visibilityOffFilled
                }
                IconButton(
                    onClick = { passwordVisibility = !passwordVisibility },
                    enabled = mViewModel.password.value.isNotEmpty()
                ) {
                    Icon(imageVector = icon, contentDescription = null)
                }
            },
            onValueChange = { mViewModel.password.value = it },
            isError = false,
            modifier = Modifier
                .padding(top = 16.dp)
                .constrainAs(textFieldPassword) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(textFieldEmailAndPassword.bottom)

                }
        )


        //Button de registro
        Button(
            onClick = { navHostController.navigate(ScreensNavigation.BachelorsScreen.route) },
            shape = MaterialTheme.shapes.extraSmall,
            enabled = mViewModel.isEmailValid.value && mViewModel.isPasswordValid.value && mViewModel.isUsernameValid.value,
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.White,
                containerColor = Color.Black
            ),
            modifier = Modifier
                .padding(top = 64.dp)
                .width(220.dp)
                .constrainAs(buttonLogin) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(textFieldPassword.bottom)
                }
        ) {
            Text(text = "Registrarme")
        }


        //Registro con redes sociales
        Text(
            text = "Registro con redes sociales",
            style = MaterialTheme.typography.bodySmall,
            modifier = modifier.constrainAs(txtSocialNetwork) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(bottomGuideline)
            })

        //Google button
        OutlinedButton(
            modifier = modifier
                .constrainAs(btnGoogleSignIn) {
                    bottom.linkTo(parent.bottom)
                    top.linkTo(txtSocialNetwork.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
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
    }
}
