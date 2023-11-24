package com.example.merlinproject.data.repository

import com.example.merlinproject.common.Resource
import com.example.merlinproject.domain.model.BachelorsModel
import com.example.merlinproject.domain.model.UserFirebase
import com.example.merlinproject.domain.model.UserModel
import com.example.merlinproject.domain.repository.IFirebaseUserRepository
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseUserRepositoryImpl @Inject constructor(private val usersRef: CollectionReference) :
    IFirebaseUserRepository {

    override suspend fun createNewUser(user: UserModel): Resource<Boolean> {
        return try {
            user.password = ""
            usersRef.document(user.id).set(user).await()
            Resource.Success(true)
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Failure(e)
        }
    }
}