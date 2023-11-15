package com.example.merlinproject.ui.features.auth.login

import android.util.Patterns
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.merlinproject.data.Resource
import com.example.merlinproject.domain.repository.IFirebaseAuthRepository
import com.google.android.gms.common.api.Response
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val autUsesCase: IFirebaseAuthRepository) :
    ViewModel() {

    //Email setup
    var email: MutableState<String> = mutableStateOf("")
    var isEmailValid: MutableState<Boolean> = mutableStateOf(false)
    var emailMsgResult: MutableState<String> = mutableStateOf("")

    //Password setup
    var password: MutableState<String> = mutableStateOf("")
    var isPasswordValid: MutableState<Boolean> = mutableStateOf(false)
    var passwordMsgResult: MutableState<String> = mutableStateOf("")

    //Login
    private val _loginFlow = MutableStateFlow<Resource<FirebaseUser>?>(null)
    val loginFlow: StateFlow<Resource<FirebaseUser>?> = _loginFlow

    //Register
    private val _signUpFlow = MutableStateFlow<Resource<FirebaseUser>?>(null)
    val signUpFlow: StateFlow<Resource<FirebaseUser>?> = _signUpFlow

    /*   val currentUser: FirebaseUser?
           get() = repository.currentUser

       init {
           if (repository.currentUser != null) {
               _loginFlow.value = Resource.Success(repository.currentUser!!)
           }
       }*/

    fun login() = viewModelScope.launch {
        _loginFlow.value = Resource.Loading
        val result = autUsesCase.login(email.value, password.value)
        _loginFlow.value = result
    }


    fun validateEmail() {
        //saber si el Email valido
        if (Patterns.EMAIL_ADDRESS.matcher(email.value).matches()) {
            //Accedemos al valor
            isEmailValid.value = true
            emailMsgResult.value = "El correo es válido.♥ "
        } else {
            //Accedemos al valor
            isEmailValid.value = false
            emailMsgResult.value = "El correo no es válido."
        }
    }

    fun validatePassword() {
        if (password.value.length >= 6) {
            isPasswordValid.value = true
            passwordMsgResult.value = "Formato correcto."
        } else {
            isPasswordValid.value = false
            passwordMsgResult.value = "Introduce almenos 6 carácteres."
        }
    }

    /*   fun login(email: String, password: String) = viewModelScope.launch {
           _loginFlow.value = Resource.Loading
           val result = repository.login(email, password)
           _loginFlow.value = result
       }

        fun login() = viewModelScope.launch {
        _loginFlow.value = Resource.Loading
        val result = autUsesCase.login(email.value, password.value)
        _loginFlow.value = result
    }

       fun signUp(name: String, email: String, password: String) = viewModelScope.launch {
           _signUpFlow.value = Resource.Loading
           val result = repository.signUp(name, email, password)
           _signUpFlow.value = result
       }

       fun logout() {
           repository.logout()
           _loginFlow.value = null
           _signUpFlow.value = null
       }*/
}