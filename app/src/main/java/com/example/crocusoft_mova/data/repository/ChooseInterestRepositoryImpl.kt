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

    override suspend fun addTags(tags: List<String>): ContentState<Unit> =
        suspendCancellableCoroutine { continuation ->
            try {


                firebaseAuth.currentUser?.let { user ->

                    firebaseCollection.whereEqualTo("userId", user.uid).get().addOnSuccessListener {
                        if (!it.isEmpty) {
                            continuation.resume(ContentState.Success(Unit))
                            return@addOnSuccessListener
                        }
                    }

                    firebaseCollection.add(
                        hashMapOf(
                            "userId" to user.uid,
                            "tags" to tags,
                            "email" to user.email,
                            "fullName" to "",
                            "gender" to "",
                            "nickname" to "",
                            "phoneNumber" to "",
                            "pin" to "",
                            "profileUri" to "",


                            )
                    ).addOnSuccessListener {
                        continuation.resume(ContentState.Success(Unit))

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