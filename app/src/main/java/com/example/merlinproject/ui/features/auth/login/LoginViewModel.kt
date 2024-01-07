package com.example.merlinproject.ui.features.auth.login

import android.util.Patterns
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.merlinproject.common.Resource
import com.example.merlinproject.domain.usescase.auth.login.AuthUsesCase
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val authUsesCase: AuthUsesCase) :
    ViewModel() {

    //email
    var email: MutableState<String> = mutableStateOf("")
    var emailValidate: MutableState<Boolean> = mutableStateOf(false)
    var emailErrMsg: MutableState<String> = mutableStateOf("")

    //password
    var password: MutableState<String> = mutableStateOf("")
    var passwordValidate: MutableState<Boolean> = mutableStateOf(false)
    var passwordErrMsg: MutableState<String> = mutableStateOf("")


    private val _loginFlow = MutableStateFlow<Resource<FirebaseUser>?>(null)
    val loginFlow: StateFlow<Resource<FirebaseUser>?> = _loginFlow

    //Login flow
    fun firebaseLoginWithEmailAndPassword() = viewModelScope.launch {
        _loginFlow.value = Resource.Loading
        val result = authUsesCase.login.invoke(email.value, password.value)
        _loginFlow.value = result
    }

    //enable button
    var isEnableFirebaseLoginButton = false

    private fun enabledFirebaseLoginButton() {
        isEnableFirebaseLoginButton = emailValidate.value && passwordValidate.value
    }

    //current user
    private val currentUser = authUsesCase.getCurrentUser()

    init {
        if (authUsesCase.getCurrentUser() != null) {
            _loginFlow.value = Resource.Success(currentUser!!)
        }
    }

    //Validate email
    fun validateEmail() {
        if (Patterns.EMAIL_ADDRESS.matcher(email.value).matches()) {
            emailValidate.value = true
            emailErrMsg.value = "Correo válido"
        } else {
            emailValidate.value = false
            emailErrMsg.value = "Correo no válido"
        }
        enabledFirebaseLoginButton()
    }

    //Validate password
    fun validatePassword() {
        if (password.value.length >= 6) {
            passwordValidate.value = true
            passwordErrMsg.value = "Contraseña valida"
        } else {
            passwordValidate.value = false
            passwordErrMsg.value = "Introduce almenos 6 carácteres."
        }
        enabledFirebaseLoginButton()
    }


}