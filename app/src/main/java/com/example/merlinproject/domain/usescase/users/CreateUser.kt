package com.example.merlinproject.domain.usescase.users

import com.example.merlinproject.domain.model.UserFirebase
import com.example.merlinproject.domain.model.UserModel
import com.example.merlinproject.domain.repository.IFirebaseUserRepository
import javax.inject.Inject

class CreateUser @Inject constructor(private val repository: IFirebaseUserRepository) {
    suspend operator fun invoke(userModel: UserModel) {
        repository.createNewUser(userModel)
    }
}