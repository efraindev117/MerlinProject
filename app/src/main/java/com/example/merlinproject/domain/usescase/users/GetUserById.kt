package com.example.merlinproject.domain.usescase.users

import com.example.merlinproject.domain.repository.IFirebaseUserRepository
import javax.inject.Inject

class GetUserById @Inject constructor(private val repository: IFirebaseUserRepository) {
    operator fun invoke(id:String) = repository.getUserById(id)
}