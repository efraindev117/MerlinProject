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
import com.example.merlinproject.domain.usescase.campus.CampusUsesCase
import com.example.merlinproject.domain.usescase.campus.GetCampus
import com.example.merlinproject.domain.usescase.users.CreateUser
import com.example.merlinproject.domain.usescase.users.UsersUsesCase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.*

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class FirebaseUsersCollection
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class FirebaseCampusCollection
@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    fun provideFirebaseAuthInstance(): FirebaseAuth = FirebaseAuth.getInstance()

    // Firestore
    @Provides
    fun provideFirestoreInstance(): FirebaseFirestore = Firebase.firestore

    // Referencia de la colección "Users"
    @Provides
    @FirebaseUsersCollection
    fun provideUsersCollection(db: FirebaseFirestore): CollectionReference = db.collection("Users")

    // Referencia de la colección "Plantel"
    @Provides
    @FirebaseCampusCollection
    fun provideCampusCollection(db: FirebaseFirestore): CollectionReference = db.collection("Plantel")

    // Repositorios
    @Provides
    fun provideAuthRepository(impl: FirebaseAuthRepositoryImp): IFirebaseAuthRepository = impl

    @Provides
    fun provideUsersRepository(impl: FirebaseUserRepositoryImpl): IFirebaseUserRepository = impl

    @Provides
    fun provideCampusRepository(impl: FirebaseCampusRepositoryImpl): IFirebaseCampusRepository = impl

    // Casos de uso
    @Provides
    fun provideAuthUsesCase(repository: IFirebaseAuthRepository): AuthUsesCase =
        AuthUsesCase(
            getCurrentUser = GetCurrentUser(repository),
            login = Login(repository),
            logout = Logout(repository),
            signUp = SignUp(repository)
        )

    @Provides
    fun provideUsersUsesCase(repository: IFirebaseUserRepository): UsersUsesCase =
        UsersUsesCase(createUser = CreateUser(repository))

    @Provides
    fun provideCampusUsesCase(repository: IFirebaseCampusRepository): CampusUsesCase =
        CampusUsesCase(getCampus = GetCampus(repository))
}