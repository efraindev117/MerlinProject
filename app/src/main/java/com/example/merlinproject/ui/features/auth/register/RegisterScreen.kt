package com.example.merlinproject.ui.features.auth.register

import TextFieldMerlin
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import com.example.merlinproject.common.Resource
import com.example.merlinproject.ui.features.auth.register.composable.BoxFormRegister
import com.example.merlinproject.ui.navigation.graph.AuthScreen
import com.example.merlinproject.ui.theme.MerlinProjectIcons
import com.example.merlinproject.ui.theme.lexendFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    navHostController: NavHostController = rememberNavController(),
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.register_screen_title),
                        fontFamily = lexendFontFamily,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navHostController.navigate(AuthScreen.WelcomeScreen.route) }) {
                        Icon(
                            imageVector = MerlinProjectIcons.navigateToBack,
                            contentDescription = "Navigate To Back"
                        )
                    }
                },
            )
        }
    ) { innerPadding ->
        ScreenRegisterContent(modifier, innerPadding, navHostController)
    }
}

@Composable
fun ScreenRegisterContent(
    modifier: Modifier,
    innerPadding: PaddingValues,
    navHostController: NavHostController,
    mViewModel: RegisterViewModel = hiltViewModel(),

    ) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxSize()
            .padding(innerPadding)
    ) {
        val (
            boxFormRegister,
            buttonLogin,
            txtSocialNetwork,
            btnGoogleSignIn) = createRefs()
        val middleGuideline = createGuidelineFromTop(.1f)
        val bottomGuideline = createGuidelineFromBottom(.3f)

        BoxFormRegister(modifier.constrainAs(boxFormRegister){
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            bottom.linkTo(buttonLogin.top)
        })


        //Button de registro
        Button(
            onClick = { mViewModel.firebaseOnSignUpWithEmailAndPassword() },
            shape = MaterialTheme.shapes.extraSmall,
            enabled = mViewModel.isEnableFirebaseRegisterButton,
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
                    top.linkTo(boxFormRegister.bottom)
                }
        ) {
            Text(
                text = "Registrarme",
                fontFamily = lexendFontFamily,
                fontWeight = FontWeight.SemiBold
            )
        }


        //Registro con redes sociales
        Text(
            text = "Registro con redes sociales",
            fontFamily = lexendFontFamily,
            fontWeight = FontWeight.Normal,

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

    mViewModel.signUpFlow.collectAsState().value?.let { resourceState ->
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
                    mViewModel.createUser()
                    navHostController.popBackStack(
                        AuthScreen.RegisterScreen.route,
                        true
                    )
                    // navHostController.navigate(AuthScreen.BachelorsScreen.route)
                }
            }

            is Resource.Failure -> {
                // TODO: con este vamos a probar la peticion a firebase
            }
        }
    }
}
