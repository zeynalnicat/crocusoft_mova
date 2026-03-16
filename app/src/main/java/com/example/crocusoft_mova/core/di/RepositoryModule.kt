package com.example.crocusoft_mova.core.di

import com.example.crocusoft_mova.data.repository.ChooseInterestRepositoryImpl
import com.example.crocusoft_mova.data.repository.SignInRepositoryImpl
import com.example.crocusoft_mova.data.repository.SignUpRepositoryImpl
import com.example.crocusoft_mova.domain.repository.ChooseInterestRepository
import com.example.crocusoft_mova.domain.repository.SignInRepository
import com.example.crocusoft_mova.domain.repository.SignUpRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideSignUpRepository(firebaseAuth: FirebaseAuth): SignUpRepository =
        SignUpRepositoryImpl(firebaseAuth)

    @Singleton
    @Provides
    fun provideSignInRepository(firebaseAuth: FirebaseAuth): SignInRepository =
        SignInRepositoryImpl(firebaseAuth)

    @Singleton
    @Provides
    fun provideChooseInterestRepository(
        firebaseAuth: FirebaseAuth,
        firestore: FirebaseFirestore
    ): ChooseInterestRepository =
        ChooseInterestRepositoryImpl(firebaseAuth, firestore)


}