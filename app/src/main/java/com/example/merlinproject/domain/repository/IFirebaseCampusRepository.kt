package com.example.merlinproject.domain.repository

import com.example.merlinproject.common.Resource
import com.example.merlinproject.domain.model.campus.CampusModel
import kotlinx.coroutines.flow.Flow

interface IFirebaseCampusRepository {
    fun getCampusByName(): Flow<Resource<List<CampusModel>>>
}