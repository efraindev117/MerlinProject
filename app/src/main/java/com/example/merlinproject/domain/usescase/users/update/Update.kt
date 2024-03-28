package com.example.merlinproject.domain.usescase.users.update

import com.example.merlinproject.domain.model.user.UserModel
import com.example.merlinproject.domain.repository.IFirebaseUserRepository
import javax.inject.Inject

class Update @Inject constructor(private val repository: IFirebaseUserRepository) {
    suspend operator fun invoke(userModel: UserModel) {
        repository.update(userModel)
    }
}