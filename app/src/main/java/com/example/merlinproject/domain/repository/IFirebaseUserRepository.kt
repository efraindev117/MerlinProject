package com.example.merlinproject.domain.repository

import com.example.merlinproject.common.Resource
import com.example.merlinproject.domain.model.BachelorsModel
import com.example.merlinproject.domain.model.UserFirebase
import com.example.merlinproject.domain.model.UserModel
import kotlinx.coroutines.flow.Flow

interface IFirebaseUserRepository {

    suspend fun createNewUser(user: UserModel): Resource<Boolean>
}