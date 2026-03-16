package com.example.crocusoft_mova.data.repository

import com.example.crocusoft_mova.core.AppErrors
import com.example.crocusoft_mova.core.ContentState
import com.example.crocusoft_mova.core.FirebaseConstants
import com.example.crocusoft_mova.domain.repository.ChooseInterestRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import jakarta.inject.Inject
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class ChooseInterestRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firestore: FirebaseFirestore,
) : ChooseInterestRepository {

    val firebaseCollection = firestore.collection(FirebaseConstants.userCollection)

    override suspend fun checkWhetherExist(): ContentState<Boolean> =
        suspendCancellableCoroutine { continuation ->

            try {
                firebaseAuth.currentUser?.let { userId ->
                    firebaseCollection.whereEqualTo("userId", userId).get()
                        .addOnSuccessListener { snapshots ->
                            if (snapshots.isEmpty) {
                                continuation.resume(ContentState.Success(false))
                            } else {
                                continuation.resume(ContentState.Success(true))
                            }

                        }
                        .addOnFailureListener {
                            continuation.resume(
                                ContentState.Error(
                                    it.message ?: AppErrors.unknownError
                                )
                            )
                        }

                }

            } catch (e: Exception) {
                if (continuation.isActive) {
                    continuation.resume(ContentState.Error(e.message ?: AppErrors.unknownError))
                }
            }

        }
}