package com.example.merlinproject.ui.features.auth.register.composable

import TextFieldMerlin
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.merlinproject.R
import com.example.merlinproject.ui.features.auth.register.RegisterViewModel
import com.example.merlinproject.ui.theme.MerlinProjectIcons
import com.example.merlinproject.ui.theme.lexendFontFamily

@Composable
fun BoxFormRegister(
    modifier: Modifier = Modifier,
    mViewModel:RegisterViewModel = hiltViewModel()
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }
    var usernameVisibility by remember { mutableStateOf(false) }

    Box(
        modifier
            .fillMaxWidth()) {
        Column(modifier.padding(16.dp)) {

            TextFieldMerlin(
                value = mViewModel.username.value,
                validateField = { mViewModel.validateUsername() },
                supportingText = {
                    if (mViewModel.username.value.isNotEmpty()) {
                        val message = if (mViewModel.usernameValidate.value) {
                            mViewModel.usernameMsgResult.value
                        } else {
                            mViewModel.usernameMsgResult.value
                        }
                        Text(
                            text = message,
                            fontFamily = lexendFontFamily,
                            fontWeight = FontWeight.Light
                        )
                    } else {
                        Text(
                            text = "Nombre de usuario",
                            fontFamily = lexendFontFamily,
                            fontWeight = FontWeight.Light
                        )
                    }
                },
                label = stringResource(id = R.string.register_screen_username_text_field_label),
                leadingIcon = MerlinProjectIcons.usernameIcon,
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
                onValueChange = { mViewModel.username.value = it },
                visualTransformation = VisualTransformation.None,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            )

            TextFieldMerlin(
                value = mViewModel.email.value,
                validateField = { mViewModel.validateEmail() },
                supportingText = {
                    if (mViewModel.email.value.isNotEmpty()) {
                        val message = if (mViewModel.emailValidate.value) {
                            mViewModel.emailMsgResult.value
                        } else {
                            mViewModel.emailMsgResult.value
                        }
                        Text(
                            text = message,
                            fontFamily = lexendFontFamily,
                            fontWeight = FontWeight.Light
                        )
                    } else {
                        Text(
                            text = "Correo electronico",
                            fontFamily = lexendFontFamily,
                            fontWeight = FontWeight.Light
                        )
                    }
                },
                label = stringResource(id = R.string.register_screen_email_text_field_label),
                leadingIcon = MerlinProjectIcons.emailOutlined,
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
                onValueChange = { mViewModel.email.value = it },
                visualTransformation = VisualTransformation.None,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            )

            //Password
            TextFieldMerlin(
                value = mViewModel.password.value,
                validateField = { mViewModel.validatePassword() },
                supportingText = {
                    if (mViewModel.password.value.isNotEmpty()) {
                        val message = if (mViewModel.passwordValidate.value) {
                            mViewModel.passwordMsgResult.value
                        } else {
                            mViewModel.passwordMsgResult.value
                        }
                        Text(
                            text = message,
                            fontFamily = lexendFontFamily,
                            fontWeight = FontWeight.Light
                        )
                    } else {
                        Text(
                            text = "Contraseña",
                            fontFamily = lexendFontFamily,
                            fontWeight = FontWeight.Light
                        )
                    }
                },
                label = stringResource(id = R.string.register_screen_password_text_field_label),
                visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                leadingIcon = MerlinProjectIcons.passwordOutlined,
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
                modifier = Modifier
                    .padding(top = 16.dp)

            )

            //campus
            TextFieldMerlin(
                value = mViewModel.password.value,
                validateField = { mViewModel.validatePassword() },
                supportingText = {
                    if (mViewModel.password.value.isNotEmpty()) {
                        val message = if (mViewModel.passwordValidate.value) {
                            mViewModel.passwordMsgResult.value
                        } else {
                            mViewModel.passwordMsgResult.value
                        }
                        Text(
                            text = message,
                            fontFamily = lexendFontFamily,
                            fontWeight = FontWeight.Light
                        )
                    } else {
                        Text(
                            text = "Campus",
                            fontFamily = lexendFontFamily,
                            fontWeight = FontWeight.Light
                        )
                    }
                },
                label = stringResource(id = R.string.register_screen_campus_text_field_label),
                visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                leadingIcon = MerlinProjectIcons.planteldOutlined,
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
                modifier = Modifier
                    .padding(top = 16.dp)

            )

            //Profession
            TextFieldMerlin(
                value = mViewModel.password.value,
                validateField = { mViewModel.validatePassword() },
                supportingText = {
                    if (mViewModel.password.value.isNotEmpty()) {
                        val message = if (mViewModel.passwordValidate.value) {
                            mViewModel.passwordMsgResult.value
                        } else {
                            mViewModel.passwordMsgResult.value
                        }
                        Text(
                            text = message,
                            fontFamily = lexendFontFamily,
                            fontWeight = FontWeight.Light
                        )
                    } else {
                        Text(
                            text = "Licenciatura",
                            fontFamily = lexendFontFamily,
                            fontWeight = FontWeight.Light
                        )
                    }
                },
                label = stringResource(id = R.string.register_screen_lic_text_field_label),
                visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                leadingIcon = MerlinProjectIcons.schooldOutlined,
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
                modifier = Modifier
                    .padding(top = 16.dp)

            )
        }
    }
}

@Preview(apiLevel = 33)
@Composable
fun RegisterPReview(){
    BoxFormRegister()
}