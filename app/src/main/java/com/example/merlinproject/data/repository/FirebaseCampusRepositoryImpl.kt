package com.example.merlinproject.data.repository

import com.example.merlinproject.common.Resource
import com.example.merlinproject.data.di.PlantelCollection
import com.example.merlinproject.domain.model.campus.CampusModel
import com.example.merlinproject.domain.repository.IFirebaseCampusRepository
import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class FirebaseCampusRepositoryImpl @Inject constructor(
    @PlantelCollection private val campusRef: CollectionReference
) : IFirebaseCampusRepository {

    override fun getCampusByName(): Flow<Resource<List<CampusModel>>> = callbackFlow {
        val snapshotListener = campusRef.addSnapshotListener { value, error ->
            val campusResponse = if (value != null) {
                val campus = value.toObjects(CampusModel::class.java)
                Resource.Success(campus)
            } else {
                Resource.Failure(error)
            }
            trySend(campusResponse)
        }
        awaitClose {
            snapshotListener.remove()
        }
    }
}