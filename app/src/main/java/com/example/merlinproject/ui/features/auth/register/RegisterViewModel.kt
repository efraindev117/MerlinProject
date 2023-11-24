package com.example.merlinproject.ui.features.auth.register

import android.util.Patterns
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.merlinproject.common.Resource
import com.example.merlinproject.domain.model.UserModel
import com.example.merlinproject.domain.usescase.auth.login.AuthUsesCase
import com.example.merlinproject.domain.usescase.users.UsersUsesCase
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authUsesCase: AuthUsesCase,
    private val usersUsesCase: UsersUsesCase
) :
    ViewModel() {

    //Username-register
    var username: MutableState<String> = mutableStateOf("")
    var usernameValidate: MutableState<Boolean> = mutableStateOf(false)
    var usernameMsgResult: MutableState<String> = mutableStateOf("")

    //Email-register
    var email: MutableState<String> = mutableStateOf("")
    var emailValidate: MutableState<Boolean> = mutableStateOf(false)
    var emailMsgResult: MutableState<String> = mutableStateOf("")

    //password-register
    var password: MutableState<String> = mutableStateOf("")
    var passwordValidate: MutableState<Boolean> = mutableStateOf(false)
    var passwordMsgResult: MutableState<String> = mutableStateOf("")

    //register-flow
    private val _signInFlow = MutableStateFlow<Resource<FirebaseUser>?>(null)
    var signUpFlow: StateFlow<Resource<FirebaseUser>?> = _signInFlow

    //enable button
    var isEnableFirebaseRegisterButton = false

    //validate username
    fun validateUsername() {
        if (username.value.length >= 6) {
            usernameValidate.value = true
            usernameMsgResult.value = "Nombre Válido"
        } else {
            usernameValidate.value = false
            usernameMsgResult.value = "El nombre es muy corto"
        }
        enabledFirebaseRegisterButton()
    }

    //validate Email
    fun validateEmail() {
        //saber si el Email valido
        if (Patterns.EMAIL_ADDRESS.matcher(email.value).matches()) {
            //Accedemos al valor
            emailValidate.value = true
            emailMsgResult.value = "El correo es válido"
        } else {
            //Accedemos al valor
            emailValidate.value = false
            emailMsgResult.value = "El correo no es válido"
        }
        enabledFirebaseRegisterButton()
    }

    //validate password
    fun validatePassword() {
        if (password.value.length >= 6) {
            passwordValidate.value = true
            passwordMsgResult.value = "Formato correcto"
        } else {
            passwordValidate.value = false
            passwordMsgResult.value = "Introduce almenos 6 carácteres."
        }
        enabledFirebaseRegisterButton()
        // TODO: validar la confirmacion de la contraseña 
    }

    //Valdiate button signUp
    private fun enabledFirebaseRegisterButton() {
        isEnableFirebaseRegisterButton =
            emailValidate.value && passwordValidate.value && usernameValidate.value
    }

    // signUp flow
    private fun firebaseSignUpWithEmailAndPassword(userModel: UserModel) = viewModelScope.launch {
        _signInFlow.value = Resource.Loading
        val result = authUsesCase.signUp.invoke(userModel)
        _signInFlow.value = result
    }
    var user = UserModel()

    //OnsignUp
    fun firebaseOnSignUpWithEmailAndPassword() {
        user.username = username.value
        user.email = email.value
        user.password = password.value
        firebaseSignUpWithEmailAndPassword(user)
    }

    fun createUser() = viewModelScope.launch {
        user.id = authUsesCase.getCurrentUser()!!.uid
        usersUsesCase.createUser(user)
    }
}