package com.example.merlinproject.data.di

import com.example.merlinproject.data.repository.FirebaseAuthRepositoryImp
import com.example.merlinproject.domain.repository.IFirebaseAuthRepository
import com.example.merlinproject.domain.usescase.auth.login.AuthUsesCase
import com.example.merlinproject.domain.usescase.auth.login.GetCurrentUser
import com.example.merlinproject.domain.usescase.auth.login.Login
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    fun provideFirebaseAuthInstance(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun provideFirebaseFirestoreInstance(): FirebaseFirestore = Firebase.firestore

    @Provides
    fun provideAuthRepository(impl: FirebaseAuthRepositoryImp):
            IFirebaseAuthRepository = impl

    @Provides
    fun provideAuthUsesCase(repository: IFirebaseAuthRepository) = AuthUsesCase(
        getCurrentUser = GetCurrentUser(repository),
        login = Login(repository)
    )
}