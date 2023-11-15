package com.example.merlinproject.domain.usescase.auth.login

import com.example.merlinproject.domain.repository.IFirebaseAuthRepository
import javax.inject.Inject

class GetCurrentUser @Inject constructor(private val authRepository: IFirebaseAuthRepository) {
    operator fun invoke() {
        authRepository.currentUser
    }
}