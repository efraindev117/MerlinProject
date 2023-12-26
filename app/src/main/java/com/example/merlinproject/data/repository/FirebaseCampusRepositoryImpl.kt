package com.example.merlinproject.data.repository

import android.util.Log
import com.example.merlinproject.common.Constants.HUMANIDADES_Y_CIENCIAS_SOCIALES
import com.example.merlinproject.common.Constants.OFERTA_FIREBASE
import com.example.merlinproject.common.Constants.SUBCOLLECTION
import com.example.merlinproject.common.Resource
import com.example.merlinproject.data.di.PlantelCollection
import com.example.merlinproject.domain.model.campus.CampusModel
import com.example.merlinproject.domain.model.campus.DocumentModel
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

    override fun getCampusDocument(campus: String): Flow<Resource<DocumentModel?>> = callbackFlow {
        val snapshotListenerDocument = campusRef.document(campus)
            .addSnapshotListener { value, error ->
                val documentResponse = if (value != null) {
                    val firestoreData = value.toObject(DocumentModel::class.java) ?: DocumentModel()
                    Resource.Success(firestoreData)
                } else {
                    Resource.Failure(error)
                }
                trySend(documentResponse)
            }
        awaitClose {
            snapshotListenerDocument.remove()
        }
    }

    override fun getCampusByName(): Flow<Resource<List<CampusModel>>> = callbackFlow {
        val snapshotListener = campusRef.addSnapshotListener { querySnapshot, error ->
            val campusResponse = if (querySnapshot != null) {
                val campusList = mutableListOf<CampusModel>()
                for (document in querySnapshot.documents) {
                    val campus = document.toObject(CampusModel::class.java)
                    // Puedes hacer lo que quieras con el ID aquí
                    campusList.add(campus!!)
                }
                Resource.Success(campusList)
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
            .document(HUMANIDADES_Y_CIENCIAS_SOCIALES)
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

