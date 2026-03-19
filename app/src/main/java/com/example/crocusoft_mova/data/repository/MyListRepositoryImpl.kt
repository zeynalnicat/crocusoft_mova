package com.example.crocusoft_mova.data.repository

import com.example.crocusoft_mova.core.ContentState
import com.example.crocusoft_mova.core.constants.AppErrors
import com.example.crocusoft_mova.core.constants.FirebaseConstants
import com.example.crocusoft_mova.domain.models.MovieUiModel
import com.example.crocusoft_mova.domain.repository.MyListRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import jakarta.inject.Inject
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class MyListRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : MyListRepository {

    private val collection = firestore.collection(FirebaseConstants.moviesCollection)

    override suspend fun fetchMyList(): ContentState<List<MovieUiModel>> = try {
        if (firebaseAuth.currentUser == null) {
            return ContentState.Error(AppErrors.userNotFound)
        }

        val success: ContentState<List<MovieUiModel>> =
            suspendCancellableCoroutine { continuation ->
                collection.whereEqualTo("userId", firebaseAuth.currentUser?.uid).get()
                    .addOnSuccessListener {
                        if (it.isEmpty) {
                            if (continuation.isActive) {
                                continuation.resume(ContentState.Error(AppErrors.unknownError))
                            }
                        } else {

                            val movies = mutableListOf<MovieUiModel>()
                            for (document in it) {
                                val movie = MovieUiModel.mock.copy(
                                    id = (document.get("movieId") as Long).toInt(),
                                    image = document.get("image") as String,
                                    vote_average = document.get("rating") as Double
                                )
                                movies.add(movie)
                            }

                            if (continuation.isActive) {
                                continuation.resume(ContentState.Success(movies))
                            }
                        }

                    }

            }
        return success
    } catch (e: Exception) {
        return ContentState.Error(e.message ?: AppErrors.unknownError)

    }
}