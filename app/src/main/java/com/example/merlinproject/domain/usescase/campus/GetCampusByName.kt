package com.example.merlinproject.domain.usescase.campus

import com.example.merlinproject.domain.repository.IFirebaseCampusRepository
import javax.inject.Inject

class GetCampusByName @Inject constructor(private val repository: IFirebaseCampusRepository) {
    operator fun invoke() = repository.getCampusByName()
}