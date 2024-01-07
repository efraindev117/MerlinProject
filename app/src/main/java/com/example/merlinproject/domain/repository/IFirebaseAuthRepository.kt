package com.example.merlinproject.domain.repository

import com.example.merlinproject.common.Resource
import com.example.merlinproject.domain.model.UserModel
import com.google.firebase.auth.FirebaseUser

interface IFirebaseAuthRepository {
    val currentUser: FirebaseUser?
    suspend fun login(email: String, password: String): Resource<FirebaseUser>
    suspend fun signUp(user: UserModel): Resource<FirebaseUser>
    fun logout()
}