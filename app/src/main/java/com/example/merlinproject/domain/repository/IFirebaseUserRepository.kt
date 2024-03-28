package com.example.merlinproject.domain.repository

import com.example.merlinproject.common.Resource
import com.example.merlinproject.domain.model.user.UserModel
import kotlinx.coroutines.flow.Flow

//Domain module
interface IFirebaseUserRepository {
    //create user
    suspend fun createNewUser(user: UserModel): Resource<Boolean>

    //Update user info
    suspend fun update(user: UserModel): Resource<Boolean>

    //Método para traer información de firebase
    fun getUserById(id: String): Flow<UserModel>


}