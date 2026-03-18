package com.example.crocusoft_mova.data.repository

import com.example.crocusoft_mova.core.ContentState
import com.example.crocusoft_mova.core.constants.AppErrors
import com.example.crocusoft_mova.domain.repository.ProfileRepository
import com.google.firebase.auth.FirebaseAuth
import jakarta.inject.Inject
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class ProfileRepositoryImpl @Inject constructor(private val firebaseAuth: FirebaseAuth) :
    ProfileRepository {
    override suspend fun logOut(): ContentState<Unit> =
        suspendCancellableCoroutine { continuation ->
            try {
                firebaseAuth.signOut()
                continuation.resume(ContentState.Success(Unit))

            } catch (e: Exception) {
                if (continuation.isActive) {
                    continuation.resume(ContentState.Error(e.message ?: AppErrors.unknownError))
                }
            }
        }
}