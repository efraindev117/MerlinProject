package com.example.merlinproject.domain.usescase.auth.register

import com.example.merlinproject.domain.repository.IFirebaseAuthRepository
import javax.inject.Inject

class Register @Inject constructor(private val authRepository: IFirebaseAuthRepository) {
    suspend operator fun invoke(username: String, email: String, password: String) {
        authRepository.signUp(username, email, password)
    }
}