package com.example.crocusoft_mova.core.di

import com.example.crocusoft_mova.data.repository.SignUpRepositoryImpl
import com.example.crocusoft_mova.domain.repository.SignUpRepository
import com.google.firebase.auth.FirebaseAuth
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


}