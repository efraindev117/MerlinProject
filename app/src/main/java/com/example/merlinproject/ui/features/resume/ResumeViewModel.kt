package com.example.merlinproject.ui.features.resume

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.merlinproject.domain.model.user.UserModel
import com.example.merlinproject.domain.usescase.auth.login.AuthUsesCase
import com.example.merlinproject.domain.usescase.users.UsersUsesCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResumeViewModel @Inject constructor(
    private val authUsesCase: AuthUsesCase,
    private val usersUsesCase: UsersUsesCase
) : ViewModel() {

    var userData by mutableStateOf(UserModel())
        private set


    private fun getUserById() = viewModelScope.launch {
        usersUsesCase.getUserById(authUsesCase.getCurrentUser()!!.uid).collect() { userFirebase ->
            userData = userFirebase
        }
    }



    init {
        getUserById()
    }
}