package com.example.merlinproject.domain.repository

import com.example.merlinproject.common.Resource
import com.example.merlinproject.domain.model.CampusModel
import kotlinx.coroutines.flow.Flow

interface IFirebaseCampusRepository {
    fun getCampusFirebase(): Flow<Resource<List<CampusModel>>>
}