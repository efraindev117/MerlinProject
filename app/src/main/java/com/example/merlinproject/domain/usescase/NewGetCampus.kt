package com.example.merlinproject.domain.usescase

import com.example.merlinproject.domain.repository.INewCampusRepository
import javax.inject.Inject

class NewGetCampus @Inject constructor(private val repository: INewCampusRepository) {
    operator fun invoke() = repository.getCampusUniversity()
}