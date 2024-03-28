package com.example.merlinproject.domain.usescase.auth.register

import com.example.merlinproject.domain.model.user.UserModel
import com.example.merlinproject.domain.repository.IFirebaseAuthRepository
import javax.inject.Inject

class SignUp @Inject constructor(private val authRepository: IFirebaseAuthRepository) {

    suspend operator fun invoke(user: UserModel) =
        authRepository.signUp(user)
}

