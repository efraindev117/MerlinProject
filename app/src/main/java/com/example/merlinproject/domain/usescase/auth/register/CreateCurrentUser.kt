package com.example.merlinproject.domain.usescase.auth.register

import com.example.merlinproject.domain.repository.IFirebaseAuthRepository
import javax.inject.Inject

class CreateCurrentUser @Inject constructor(private val authRepository: IFirebaseAuthRepository) {
    operator fun invoke() {
        authRepository.currentUser
    }
}