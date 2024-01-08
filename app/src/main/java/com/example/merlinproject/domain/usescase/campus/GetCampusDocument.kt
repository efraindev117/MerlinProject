package com.example.merlinproject.domain.usescase.campus

import com.example.merlinproject.domain.repository.IFirebaseCampusRepository
import javax.inject.Inject

class GetCampusDocument @Inject constructor(private val repository: IFirebaseCampusRepository) {
    operator fun invoke(campus: String) = repository.getCampusDocument(campus)
}