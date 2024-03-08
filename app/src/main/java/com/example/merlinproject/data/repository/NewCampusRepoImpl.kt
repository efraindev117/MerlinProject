package com.example.merlinproject.data.repository

import com.example.merlinproject.common.Resource
import com.example.merlinproject.data.di.NewCampusCollection
import com.example.merlinproject.domain.model.campus.uuuuuuu.CampusUniversityModel
import com.example.merlinproject.domain.repository.INewCampusRepository
import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class NewCampusRepoImpl @Inject constructor(
    @NewCampusCollection private val campusRefCollection: CollectionReference
) : INewCampusRepository {

    override fun getCampusUniversity(): Flow<Resource<List<CampusUniversityModel>>> = callbackFlow {
        val snapshotListener = campusRefCollection.addSnapshotListener { snapshot, e ->
            Resource.Loading
            val campusResponse = if (snapshot != null) {
                val campus = snapshot.toObjects(CampusUniversityModel::class.java)
                Resource.Success(campus)
            } else {
                Resource.Failure(e)
            }
            trySend(campusResponse)
        }
        awaitClose {
            snapshotListener.remove()
        }
    }
}