package com.example.merlinproject.data.di

import com.example.merlinproject.data.repository.FirebaseAuthRepositoryImp
import com.example.merlinproject.data.repository.FirebaseUserRepositoryImpl
import com.example.merlinproject.domain.repository.IFirebaseAuthRepository
import com.example.merlinproject.domain.repository.IFirebaseUserRepository
import com.example.merlinproject.domain.usescase.auth.login.AuthUsesCase
import com.example.merlinproject.domain.usescase.auth.login.GetCurrentUser
import com.example.merlinproject.domain.usescase.auth.login.Login
import com.example.merlinproject.domain.usescase.auth.login.Logout
import com.example.merlinproject.domain.usescase.auth.register.SignUp
import com.example.merlinproject.domain.usescase.users.CreateUser
import com.example.merlinproject.domain.usescase.users.UsersUsesCase
import com.example.merlinproject.domain.usescase.users.getBachelors
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
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

    //Firestore
    @Provides
    fun provideFirestoreInstance(): FirebaseFirestore = Firebase.firestore

    //Referencia de la collection
    @Provides
    fun provideUsersCollection(db: FirebaseFirestore):
            CollectionReference = db.collection("Users")

    //implemntar repositorios
    @Provides
    fun provideAuthRepository(impl: FirebaseAuthRepositoryImp):
            IFirebaseAuthRepository = impl

    @Provides
    fun provideUsersRepository(impl: FirebaseUserRepositoryImpl):
            IFirebaseUserRepository = impl

    //uses case
    @Provides
    fun provideAuthUsesCase(repository: IFirebaseAuthRepository) = AuthUsesCase(
        getCurrentUser = GetCurrentUser(repository),
        login = Login(repository),
        logout = Logout(repository),
        signUp = SignUp(repository)
    )

    @Provides
    fun provideUsersUsesCase(repository: IFirebaseUserRepository) = UsersUsesCase(
        createUser = CreateUser(repository),
    )
}