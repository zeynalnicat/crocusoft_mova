package com.example.crocusoft_mova.data.repository

import android.util.Log
import com.example.crocusoft_mova.core.ContentState
import com.example.crocusoft_mova.core.constants.AppErrors
import com.example.crocusoft_mova.core.constants.FirebaseConstants
import com.example.crocusoft_mova.domain.models.ProfileUiModel
import com.example.crocusoft_mova.domain.repository.ProfileRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import jakarta.inject.Inject
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.tasks.await
import kotlin.coroutines.resume

class ProfileRepositoryImpl @Inject constructor(private val firebaseAuth: FirebaseAuth, private val fireStore : FirebaseFirestore) :
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

    override suspend fun getCurrentUser(): ProfileUiModel? {
        val user = firebaseAuth.currentUser ?: return null

        return try {
            val querySnapshot = fireStore.collection(FirebaseConstants.userCollection)
                .whereEqualTo("userId", user.uid)
                .get()
                .await()

            if (!querySnapshot.isEmpty) {
                val document = querySnapshot.documents[0]

                ProfileUiModel(
                    profileId = user.uid,
                    email = user.email ?: "",
                    fullName = document.getString("fullName") ?: "",
                    nickName = document.getString("nickname") ?: "",
                    phoneNumber = document.getString("phoneNumber") ?: "",
                    gender = document.getString("gender") ?: "",
                    imageUri = document.getString("profileUri") ?: ""
                )
            } else {
                null
            }
        } catch (e: Exception) {
            Log.e("getCurrentUser", "Error: ${e.message}")
            null
        }
    }

}