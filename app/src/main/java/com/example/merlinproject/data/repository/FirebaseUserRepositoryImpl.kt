package com.example.merlinproject.data.repository

import com.example.merlinproject.common.Resource
import com.example.merlinproject.data.di.UsersCollection
import com.example.merlinproject.domain.model.user.UserModel
import com.example.merlinproject.domain.repository.IFirebaseUserRepository
import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseUserRepositoryImpl @Inject constructor(
    @UsersCollection private val usersRef: CollectionReference
) :
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

    override suspend fun update(user: UserModel): Resource<Boolean> {
        return try {
            user.password = ""
            val map: MutableMap<String,Any> = HashMap()
            map["username"] = user.username
            map["profileImg"] = user.profileImg
            usersRef.document(user.id).update(map).await()
            Resource.Success(true)
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Failure(e)
        }
    }

    override fun getUserById(id: String): Flow<UserModel> = callbackFlow {
        val snapshotListener = usersRef.document(id).addSnapshotListener { snapshotListener, e ->
            val user = snapshotListener?.toObject(UserModel::class.java) ?: UserModel()
            trySend(user)
        }
        awaitClose {
            snapshotListener.remove()
        }
    }
}