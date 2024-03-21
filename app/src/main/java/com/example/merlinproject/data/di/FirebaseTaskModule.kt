package com.example.merlinproject.data.di

import com.example.merlinproject.data.repository.FirebaseAuthRepositoryImp
import com.example.merlinproject.data.repository.FirebaseUserRepositoryImpl
import com.example.merlinproject.data.repository.NewCampusRepoImpl
import com.example.merlinproject.domain.repository.IFirebaseAuthRepository
import com.example.merlinproject.domain.repository.IFirebaseUserRepository
import com.example.merlinproject.domain.repository.INewCampusRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class FirebaseTaskModule {

    @Binds
    abstract fun provideAuthRepository(impl: FirebaseAuthRepositoryImp):
            IFirebaseAuthRepository

    @Binds
    abstract fun provideUsersRepository(impl: FirebaseUserRepositoryImpl):
            IFirebaseUserRepository

    @Binds
    abstract fun provideNewCampusRepository(impl: NewCampusRepoImpl):
            INewCampusRepository
}