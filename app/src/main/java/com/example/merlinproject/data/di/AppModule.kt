package com.example.merlinproject.data.di

import com.example.merlinproject.data.repository.FirebaseAuthRepositoryImp
import com.example.merlinproject.domain.repository.IFirebaseAuthRepository
import com.google.firebase.auth.FirebaseAuth
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
    fun provideAuthRepository(impl: FirebaseAuthRepositoryImp):
            IFirebaseAuthRepository = impl


}