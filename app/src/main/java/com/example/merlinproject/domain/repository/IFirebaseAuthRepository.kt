package com.example.merlinproject.domain.repository

import com.example.merlinproject.data.Resource
import com.google.firebase.auth.FirebaseUser

interface IFirebaseAuthRepository {
    val currentUser: FirebaseUser?
    suspend fun login(email: String, password: String): Resource<FirebaseUser>
    suspend fun signUp(name: String, email: String, password: String): Resource<FirebaseUser>
    fun logout()
}