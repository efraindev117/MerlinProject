package com.example.merlinproject.domain.usescase.auth.login

data class AuthUsesCase(
    val getCurrentUser: GetCurrentUser,
    val login: Login
)