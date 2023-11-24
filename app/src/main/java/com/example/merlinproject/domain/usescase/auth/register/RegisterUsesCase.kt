package com.example.merlinproject.domain.usescase.auth.register

data class RegisterUsesCase(
    val createCurrentUser: CreateCurrentUser,
    val register: SignUp
)