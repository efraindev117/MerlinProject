package com.example.merlinproject.ui.features.auth.register

import android.util.Patterns
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.merlinproject.common.Resource
import com.example.merlinproject.domain.repository.IFirebaseAuthRepository
import com.example.merlinproject.domain.usescase.auth.register.RegisterUsesCase
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val registerUsesCase: IFirebaseAuthRepository) :
    ViewModel() {
    //Username-register
    var username: MutableState<String> = mutableStateOf("")
    var isUsernameValid: MutableState<Boolean> = mutableStateOf(false)
    var usernameMsgResult: MutableState<String> = mutableStateOf("")

    //Email-register
    var email: MutableState<String> = mutableStateOf("")
    var isEmailValid: MutableState<Boolean> = mutableStateOf(false)
    var emailMsgResult: MutableState<String> = mutableStateOf("")

    //password-register
    var password: MutableState<String> = mutableStateOf("")
    var isPasswordValid: MutableState<Boolean> = mutableStateOf(false)
    var passwordMsgResult: MutableState<String> = mutableStateOf("")

    //register-flow
    private val _registerFlow = MutableStateFlow<Resource<FirebaseUser>?>(null)
    var registerFlow: StateFlow<Resource<FirebaseUser>?> = _registerFlow


    fun register() = viewModelScope.launch {
        _registerFlow.value = Resource.Loading
        val result = registerUsesCase.signUp(
            name = username.value,
            email = email.value,
            password = password.value
        )
        //_registerFlow.value = result
    }

    fun validateEmail() {
        //saber si el Email valido
        if (Patterns.EMAIL_ADDRESS .matcher(email.value).matches()) {
            //Accedemos al valor
            isEmailValid.value = true
            emailMsgResult.value = "El correo es válido"
        } else {
            //Accedemos al valor
            isEmailValid.value = false
            emailMsgResult.value = "El correo no es válido"
        }
    }

    fun validatePassword() {
        if (password.value.length >= 6) {
            isPasswordValid.value = true
            passwordMsgResult.value = "Formato correcto"
        } else {
            isPasswordValid.value = false
            passwordMsgResult.value = "Introduce almenos 6 carácteres."
        }
    }

    fun validateUsername(){
        if (username.value.length >= 6){
            isUsernameValid.value = true
            usernameMsgResult.value = "Nombre Válido"
        }else{
            isUsernameValid.value = false
            usernameMsgResult.value = "El nombre es muy corto"
        }
    }


}