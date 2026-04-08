package com.example.crocusoft_mova.data.repository

import com.example.crocusoft_mova.core.ContentState
import com.example.crocusoft_mova.core.constants.AppErrors
import com.example.crocusoft_mova.core.constants.FirebaseConstants
import com.example.crocusoft_mova.domain.models.MovieDetailUiModel
import com.example.crocusoft_mova.domain.repository.BookmarkRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class BookmarkRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : BookmarkRepository {

    private fun getBookmarkCollection() = auth.currentUser?.uid?.let { userId ->
        firestore.collection(FirebaseConstants.userCollection)
            .document(userId)
            .collection(FirebaseConstants.bookmarkCollection)
    }

    override suspend fun toggleBookmark(movie: MovieDetailUiModel): ContentState<Unit> {
        return try {
            val collection = getBookmarkCollection() ?: return ContentState.Error(AppErrors.userNotFound)
            val docRef = collection.document(movie.id.toString())

            val snapshot = docRef.get().await()
            if (snapshot.exists()) {
                docRef.delete().await()
            } else {
                val data = mapOf(
                    "id" to movie.id,
                    "title" to movie.title,
                    "image" to movie.image,
                    "vote_average" to movie.vote_average,
                    "mediaType" to movie.mediaType
                )
                docRef.set(data).await()
            }
            ContentState.Success(Unit)
        } catch (e: Exception) {
            ContentState.Error(e.message ?: AppErrors.unknownError)
        }
    }

    override fun getBookmarks(): Flow<ContentState<List<MovieDetailUiModel>>> = callbackFlow {
        val collection = getBookmarkCollection()
        if (collection == null) {
            trySend(ContentState.Error(AppErrors.userNotFound))
            close()
            return@callbackFlow
        }

        val subscription = collection.addSnapshotListener { snapshot, error ->
            if (error != null) {
                trySend(ContentState.Error(error.message ?: AppErrors.unknownError))
                return@addSnapshotListener
            }

            val movies = snapshot?.documents?.mapNotNull { doc ->
                MovieDetailUiModel(
                    id = doc.getLong("id")?.toInt() ?: 0,
                    title = doc.getString("title") ?: "",
                    vote_average = doc.getDouble("vote_average") ?: 0.0,
                    image = doc.getString("image") ?: "",
                    mediaType = doc.getString("mediaType") ?: "",
                    release_date = "", description = "", genres = emptyList(),
                    production_companies = emptyList(), language = ""
                )
            } ?: emptyList()

            trySend(ContentState.Success(movies))
        }

        awaitClose { subscription.remove() }
    }

    override fun isBookmarked(movieId: Int): Flow<Boolean> = callbackFlow {
        val collection = getBookmarkCollection()
        if (collection == null) {
            trySend(false)
            close()
            return@callbackFlow
        }

        val subscription = collection.document(movieId.toString()).addSnapshotListener { snapshot, _ ->
            trySend(snapshot?.exists() ?: false)
        }
        awaitClose { subscription.remove() }
    }
}