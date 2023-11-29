package com.example.merlinproject.domain.repository

import com.example.merlinproject.common.Resource
import com.example.merlinproject.domain.model.UserModel
import kotlinx.coroutines.flow.Flow
//Domain module
interface IFirebaseUserRepository {
    //Crear nuevo usuario
    suspend fun createNewUser(user: UserModel): Resource<Boolean>

    //Método para traer información de firebase
    fun getUserById(id: String): Flow<UserModel>
}