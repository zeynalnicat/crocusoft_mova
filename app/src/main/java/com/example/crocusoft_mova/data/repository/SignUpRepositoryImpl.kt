package com.example.crocusoft_mova.data.repository

import com.example.crocusoft_mova.core.AppErrors
import com.example.crocusoft_mova.core.ContentState
import com.example.crocusoft_mova.domain.repository.SignUpRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class SignUpRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : SignUpRepository {


    override suspend fun signUp(
        email: String,
        password: String
    ): ContentState<Unit> =
        try {
            if (email.isBlank() || password.isBlank()) {
                return ContentState.Error(AppErrors.emptyField)
            }

            val res = firebaseAuth
                .createUserWithEmailAndPassword(email, password)
                .await()
            if (res.user == null) {
                return ContentState.Error(AppErrors.unknownError)
            }
            ContentState.Success(Unit)

        } catch (e: Exception) {
            return ContentState.Error(e.message ?: AppErrors.unknownError)
        }


}