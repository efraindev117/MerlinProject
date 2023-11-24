package com.example.merlinproject.domain.usescase.auth.login

import com.example.merlinproject.domain.usescase.auth.register.SignUp

data class AuthUsesCase(
    val getCurrentUser: GetCurrentUser,
    val login: Login,
    val logout: Logout,
    val signUp: SignUp
)