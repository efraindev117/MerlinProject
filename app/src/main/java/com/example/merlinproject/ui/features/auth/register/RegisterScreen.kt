package com.example.merlinproject.ui.features.auth.register

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.merlinproject.R
import com.example.merlinproject.ui.features.auth.login.LoginViewModel
import com.example.merlinproject.ui.features.auth.login.ScreenContent
import com.example.merlinproject.ui.features.auth.login.TextFieldMerlin
import com.example.merlinproject.ui.navigation.ScreensNavigation
import com.example.merlinproject.ui.theme.MerlinProjectIcons

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    navHostController: NavHostController,
    modifier: Modifier = Modifier,
    mViewModel: LoginViewModel = hiltViewModel() ) {
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
        ScreenRegisterContent(modifier, innerPadding, mViewModel ,navHostController)
    }
}

@Composable
fun ScreenRegisterContent(
    modifier: Modifier,
    innerPadding: PaddingValues,
    mViewModel: LoginViewModel,
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
        val bottomGuideline = createGuidelineFromBottom(.3f)
        val context = LocalContext.current
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var loading by remember { mutableStateOf(false) }

        TextFieldMerlin(
            value = mViewModel.email.value,
            supportingText = { Text(text = "Correo electronico") },
            label = { Text(text = "Email", color = Color.Black) },
            placeholder = { Text(text = "Texto de placeHolder") },
            leadingIcon = { Icon(imageVector = MerlinProjectIcons.emailOutlined, contentDescription = null) },
            trailingIcon = { Icon(imageVector = MerlinProjectIcons.cancelFilled, contentDescription = null) },
            onValueChange = { mViewModel.email.value = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.constrainAs(textFieldEmailAndPassword) {
                top.linkTo(middleGuideline)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        ) {

        }
        TextFieldMerlin(
            value = mViewModel.password.value,
            supportingText = { Text(text = "Contraseña", color = Color.Black) },
            label = { Text(text = "password", color = Color.Black) },
            placeholder = { Text(text = "") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            leadingIcon = { Icon(imageVector = MerlinProjectIcons.passwordOutlined, contentDescription = null) },
            trailingIcon = { Icon(imageVector = MerlinProjectIcons.visibilityFilled, contentDescription = null) },
            onValueChange = { mViewModel.password.value = it },
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
            Text(text = "Registrarme")
        }


        Text(
            text = "Registro con redes sociales",
            style = MaterialTheme.typography.bodySmall,
            modifier = modifier.constrainAs(txtSocialNetwork) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(bottomGuideline)
            })
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
