package com.example.merlinproject.domain.repository

import com.example.merlinproject.common.Resource
import com.example.merlinproject.domain.model.filter.LicenciaturaData
import kotlinx.coroutines.flow.Flow

interface IFirestoreFilterDataRepository {
    suspend fun filterLicenciatura() : Flow<Resource<LicenciaturaData>>

}