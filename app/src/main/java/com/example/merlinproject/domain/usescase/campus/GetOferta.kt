package com.example.merlinproject.domain.usescase.campus

import com.example.merlinproject.domain.repository.IFirebaseCampusRepository
import javax.inject.Inject

class GetOferta @Inject constructor(private val repository: IFirebaseCampusRepository ){
    operator fun invoke() = repository.getOferta()
}