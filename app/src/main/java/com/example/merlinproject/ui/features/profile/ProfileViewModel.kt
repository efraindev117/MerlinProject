package com.example.merlinproject.ui.features.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.merlinproject.domain.model.UserModel
import com.example.merlinproject.domain.usescase.auth.login.AuthUsesCase
import com.example.merlinproject.domain.usescase.users.UsersUsesCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authUsesCase: AuthUsesCase,
    private val usersUsesCase: UsersUsesCase
) : ViewModel() {

    var userData by mutableStateOf(UserModel())
        private set

    private fun getUserById() = viewModelScope.launch {
        usersUsesCase.getUserById(authUsesCase.getCurrentUser()!!.uid).collect(){ firebasedata ->
            userData = firebasedata
        }
    }


    init {
        //Inicia la funcion que trae la información en tiempo real
        getUserById()
    }


    fun logout() {
        authUsesCase.logout()
        // TODO: Modificar la vista de ProfileScreen e implementar el button con esta accion
    }


}