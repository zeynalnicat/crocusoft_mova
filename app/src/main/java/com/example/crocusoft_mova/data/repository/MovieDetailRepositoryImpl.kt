package com.example.crocusoft_mova.data.repository

import com.example.crocusoft_mova.core.ContentState
import com.example.crocusoft_mova.core.constants.AppErrors
import com.example.crocusoft_mova.core.constants.FirebaseConstants
import com.example.crocusoft_mova.data.mappers.toUiModel
import com.example.crocusoft_mova.data.service.remote.ApiService
import com.example.crocusoft_mova.data.service.remote.model.MovieDetailModel
import com.example.crocusoft_mova.domain.models.MovieDetailUiModel
import com.example.crocusoft_mova.domain.models.MovieUiModel
import com.example.crocusoft_mova.domain.repository.MovieDetailRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import jakarta.inject.Inject
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class MovieDetailRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val firebaseAuth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) :
    MovieDetailRepository {

    private val collection = firestore.collection(FirebaseConstants.moviesCollection)

    override suspend fun getMovieDetail(movieId: Int): ContentState<MovieDetailUiModel> = try {
        val res = apiService.fetchMovieDetail(movieId)

        val isAdded = suspendCancellableCoroutine { continuation ->
            collection.whereEqualTo("userId", firebaseAuth.currentUser?.uid)
                .whereEqualTo("movieId", res.id)
                .get()
                .addOnSuccessListener { snapshots ->
                    if (continuation.isActive) {
                        continuation.resume(snapshots.documents.isNotEmpty())
                    }
                }
                .addOnFailureListener {
                    if (continuation.isActive) {
                        continuation.resume(false)
                    }
                }
        }

        ContentState.Success(res.toUiModel().copy(isAdded = isAdded))
    } catch (e: Exception) {
        ContentState.Error(e.message ?: AppErrors.unknownError)
    }

    override suspend fun addMovieToWatchList(movie: MovieDetailUiModel): ContentState<Unit> =
        suspendCancellableCoroutine { continuation ->

            val user = firebaseAuth.currentUser
            if (user == null) {
                if (continuation.isActive) {
                    continuation.resume(ContentState.Error(AppErrors.userNotFound))
                }
                return@suspendCancellableCoroutine
            }

            try {
                if (movie.isAdded) {
                    collection.whereEqualTo("userId", user.uid)
                        .whereEqualTo("movieId", movie.id)
                        .get()
                        .addOnSuccessListener { snapshots ->
                            if (snapshots.isEmpty) {
                                if (continuation.isActive) {
                                    continuation.resume(ContentState.Success(Unit))
                                }
                                return@addOnSuccessListener
                            }
                            
                            val batch = firestore.batch()
                            for (document in snapshots) {
                                batch.delete(document.reference)
                            }
                            
                            batch.commit().addOnSuccessListener {
                                if (continuation.isActive) {
                                    continuation.resume(ContentState.Success(Unit))
                                }
                            }.addOnFailureListener { e ->
                                if (continuation.isActive) {
                                    continuation.resume(
                                        ContentState.Error(
                                            e.message ?: AppErrors.unknownError
                                        )
                                    )
                                }
                            }
                        }
                        .addOnFailureListener { e ->
                            if (continuation.isActive) {
                                continuation.resume(
                                    ContentState.Error(
                                        e.message ?: AppErrors.unknownError
                                    )
                                )
                            }
                        }

                } else {
                    collection.add(
                        hashMapOf(
                            "userId" to user.uid,
                            "image" to movie.image,
                            "rating" to movie.vote_average,
                            "movieId" to movie.id
                        )
                    )
                        .addOnSuccessListener {
                            if (continuation.isActive) {
                                continuation.resume(ContentState.Success(Unit))
                            }
                        }
                        .addOnFailureListener {
                            if (continuation.isActive) {
                                continuation.resume(
                                    ContentState.Error(
                                        it.message ?: AppErrors.unknownError
                                    )
                                )
                            }
                        }
                }


            } catch (e: Exception) {
                if (continuation.isActive) {
                    continuation.resume(ContentState.Error(e.message ?: AppErrors.unknownError))
                }
            }

        }


}
