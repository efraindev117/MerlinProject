package com.example.merlinproject.domain.usescase.auth.login

import com.example.merlinproject.domain.repository.IFirebaseAuthRepository
import javax.inject.Inject

class Logout @Inject constructor(val repository: IFirebaseAuthRepository) {
    operator fun invoke() = repository.logout()
}