package com.example.merlinproject.domain.usescase.campus

import com.example.merlinproject.domain.repository.IFirebaseCampusRepository
import javax.inject.Inject

class GetCampus @Inject constructor(private val repository: IFirebaseCampusRepository) {

    operator fun invoke() = repository.getCampusByName()
}