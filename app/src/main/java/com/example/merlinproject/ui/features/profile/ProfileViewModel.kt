package com.example.merlinproject.ui.features.profile

import androidx.lifecycle.ViewModel
import com.example.merlinproject.domain.usescase.auth.login.AuthUsesCase
import com.example.merlinproject.domain.usescase.users.UsersUsesCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val authUsesCase: AuthUsesCase) : ViewModel() {

    fun logout(){
        authUsesCase.logout()
        // TODO: Modificar la vista de ProfileScreen e implementar el button con esta accion
    }
}