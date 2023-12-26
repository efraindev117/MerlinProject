package com.example.merlinproject.domain.repository

import com.example.merlinproject.common.Resource
import com.example.merlinproject.domain.model.campus.CampusModel
import com.example.merlinproject.domain.model.campus.DocumentModel
import com.example.merlinproject.domain.model.campus.OfertaAcademica
import kotlinx.coroutines.flow.Flow

interface IFirebaseCampusRepository {
    fun getCampusByName(): Flow<Resource<List<CampusModel>>>
    fun getOferta(): Flow<Resource<List<OfertaAcademica>>>
    fun getCampusDocument(campus: String): Flow<Resource<DocumentModel?>>
}