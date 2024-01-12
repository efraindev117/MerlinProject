package com.example.merlinproject.data.repository

import android.util.Log
import com.example.merlinproject.common.Resource
import com.example.merlinproject.data.di.LicenciaturaCollection
import com.example.merlinproject.domain.model.filter.LicenciaturaData
import com.example.merlinproject.domain.repository.IFirestoreFilterDataRepository
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.Query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class FirestoreFilterRepoImpl @Inject constructor(
    @LicenciaturaCollection private val documentRef: Query
) : IFirestoreFilterDataRepository {
    override suspend fun filterLicenciatura(): Flow<Resource<LicenciaturaData>> = callbackFlow {

        }
    }
