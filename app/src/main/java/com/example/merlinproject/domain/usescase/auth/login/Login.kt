package com.example.merlinproject.domain.usescase.auth.login

import com.example.merlinproject.domain.repository.IFirebaseAuthRepository
import javax.inject.Inject
class Login @Inject constructor(private val authRepository: IFirebaseAuthRepository) {
    suspend operator fun invoke(email: String, password: String) =
        authRepository.login(email, password)
}