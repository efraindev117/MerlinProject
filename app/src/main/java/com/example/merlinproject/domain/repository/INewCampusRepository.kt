package com.example.merlinproject.domain.repository

import com.example.merlinproject.common.Resource
import com.example.merlinproject.domain.model.campus.CampusUniversityModel
import kotlinx.coroutines.flow.Flow

interface INewCampusRepository {
    fun getCampusUniversity(): Flow<Resource<List<CampusUniversityModel>>>
}