package com.example.merlinproject.data.repository

import android.content.ContentValues.TAG
import android.util.Log
import com.example.merlinproject.common.Constants
import com.example.merlinproject.common.Constants.ALGO
import com.example.merlinproject.common.Constants.OFERTA_FIREBASE
import com.example.merlinproject.common.Constants.SUBCOLLECTION
import com.example.merlinproject.common.Resource
import com.example.merlinproject.data.di.PlantelCollection
import com.example.merlinproject.domain.model.campus.CampusModel
import com.example.merlinproject.domain.model.campus.OfertaAcademica
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

    override fun getOferta(): Flow<Resource<List<OfertaAcademica>>> = callbackFlow {

        val documentRef = campusRef
            .document(OFERTA_FIREBASE)
            .collection(SUBCOLLECTION)
            .document(ALGO)
        val snapshotListener = documentRef.addSnapshotListener { value, error ->
            val response = if (value != null) {
                val oferta = value.toObject(OfertaAcademica::class.java)
                oferta?.let {
                    Resource.Success(listOf(it))
                } ?: Resource.Failure(Exception("Document is null"))
            } else {
                Resource.Failure(error)
            }
            trySend(response)
        }

        awaitClose {
            snapshotListener.remove()
        }
    }
}

/*override fun getOferta(): Flow<Resource<List<OfertaAcademica>>> = callbackFlow {

      val documentRef = campusRef
          .document(Constants.OFERTA_FIREBASE)
          .collection(Constants.SUBCOLLECTION)
          .document(Constants.ALGO)
      val snapshotListener = documentRef.addSnapshotListener { value, error ->
          val response = if (value != null) {
              val oferta = value.toObject(OfertaAcademica::class.java)
              oferta?.let {
                  Resource.Success(listOf(it))
              } ?: Resource.Failure(Exception("Document is null"))
          } else {
              Resource.Failure(error)
          }
          trySend(response)
      }

      awaitClose {
          snapshotListener.remove()
      }
  }*/
/*override fun getCampusByName(): Flow<Resource<List<CampusModel>>> = callbackFlow {
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
 }*/
