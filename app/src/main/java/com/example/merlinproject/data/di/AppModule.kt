package com.example.merlinproject.data.di

import com.example.merlinproject.common.Constants.CAMPUS_COLLECTION
import com.example.merlinproject.common.Constants.USERS_FIREBASE
import com.example.merlinproject.domain.repository.IFirebaseAuthRepository
import com.example.merlinproject.domain.repository.IFirebaseUserRepository
import com.example.merlinproject.domain.repository.INewCampusRepository
import com.example.merlinproject.domain.usescase.NewCampusUsesCase
import com.example.merlinproject.domain.usescase.NewGetCampus
import com.example.merlinproject.domain.usescase.auth.login.AuthUsesCase
import com.example.merlinproject.domain.usescase.auth.login.GetCurrentUser
import com.example.merlinproject.domain.usescase.auth.login.Login
import com.example.merlinproject.domain.usescase.auth.login.Logout
import com.example.merlinproject.domain.usescase.auth.register.SignUp
import com.example.merlinproject.domain.usescase.users.CreateUser
import com.example.merlinproject.domain.usescase.users.GetUserById
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

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class UsersCollection

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class NewCampusCollection

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    //Firebase authentication
    @Provides
    fun provideFirebaseAuthInstance(): FirebaseAuth = FirebaseAuth.getInstance()

    //Firestore
    @Provides
    fun provideFirestoreInstance(): FirebaseFirestore = Firebase.firestore

    //Reference de la collection
    @Provides
    @UsersCollection
    fun provideUsersCollection(db: FirebaseFirestore):
            CollectionReference = db.collection(USERS_FIREBASE)

    @Provides
    @NewCampusCollection
    fun provideNewCampusCollection(db: FirebaseFirestore):
            CollectionReference = db.collection(CAMPUS_COLLECTION)

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
        getUserById = GetUserById(repository)
    )

    @Provides
    fun provideNewCampusUsesCase(repositoy: INewCampusRepository) = NewCampusUsesCase(
        newGetCampus = NewGetCampus(repositoy)
    )
}