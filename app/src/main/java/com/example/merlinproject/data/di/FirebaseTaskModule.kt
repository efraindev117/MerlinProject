package com.example.merlinproject.data.di

import com.example.merlinproject.data.repository.FirebaseAuthRepositoryImp
import com.example.merlinproject.data.repository.FirebaseCampusRepositoryImpl
import com.example.merlinproject.data.repository.FirebaseUserRepositoryImpl
import com.example.merlinproject.domain.repository.IFirebaseAuthRepository
import com.example.merlinproject.domain.repository.IFirebaseCampusRepository
import com.example.merlinproject.domain.repository.IFirebaseUserRepository
import com.example.merlinproject.domain.usescase.auth.login.AuthUsesCase
import com.example.merlinproject.domain.usescase.auth.login.GetCurrentUser
import com.example.merlinproject.domain.usescase.auth.login.Login
import com.example.merlinproject.domain.usescase.auth.login.Logout
import com.example.merlinproject.domain.usescase.auth.register.SignUp
import com.example.merlinproject.domain.usescase.users.CreateUser
import com.example.merlinproject.domain.usescase.users.GetUserById
import com.example.merlinproject.domain.usescase.users.UsersUsesCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class FirebaseTaskModule {

    @Binds
    abstract fun provideAuthRepository(impl: FirebaseAuthRepositoryImp): IFirebaseAuthRepository

    @Binds
    abstract fun provideUsersRepository(impl: FirebaseUserRepositoryImpl):
            IFirebaseUserRepository

    @Binds
    abstract fun provideCampusRepository(impl:FirebaseCampusRepositoryImpl):
            IFirebaseCampusRepository
}