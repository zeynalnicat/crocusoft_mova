package com.example.crocusoft_mova.data.repository

import com.example.crocusoft_mova.core.AppErrors
import com.example.crocusoft_mova.core.ContentState
import com.example.crocusoft_mova.domain.repository.SignInRepository
import com.google.firebase.auth.FirebaseAuth
import jakarta.inject.Inject
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume


class SignInRepositoryImpl @Inject constructor(private val firebaseAuth: FirebaseAuth) :
    SignInRepository {
    override suspend fun signIn(
        email: String,
        password: String
    ): ContentState<Unit> = suspendCancellableCoroutine { continuation ->

        if (email.isBlank() || password.isBlank()) {
            continuation.resume(ContentState.Error(AppErrors.emptyField))
            return@suspendCancellableCoroutine
        }

        try {
            firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnFailureListener {
                    if (continuation.isActive) {
                        continuation.resume(ContentState.Error(it.message ?: AppErrors.unknownError))
                    }
                }
                .addOnSuccessListener {
                    if (continuation.isActive) {
                        continuation.resume(ContentState.Success(Unit))
                    }
                }

        } catch (e: Exception) {
            if (continuation.isActive) {
                continuation.resume(ContentState.Error(e.message ?: AppErrors.unknownError))
            }
        }
    }

}
