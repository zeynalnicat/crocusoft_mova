package com.example.crocusoft_mova.data.repository

import android.util.Log
import com.example.crocusoft_mova.core.ContentState
import com.example.crocusoft_mova.core.constants.AppErrors
import com.example.crocusoft_mova.data.mappers.toUiModel
import com.example.crocusoft_mova.data.service.remote.ApiService
import com.example.crocusoft_mova.domain.models.MovieUiModel
import com.example.crocusoft_mova.domain.repository.ExploreRepository
import javax.inject.Inject

class ExploreRepositoryImpl @Inject constructor(private val apiService: ApiService) : ExploreRepository {
    override suspend fun search(query: String): ContentState<List<MovieUiModel>> =
        try {
            val res = apiService.searchMovie(query)
            Log.i("movies",res.page.toString())
            val results = res.results
            if (results.isNullOrEmpty()) {
                ContentState.Error(AppErrors.noMovies)
            } else {
                ContentState.Success(results.map { it.toUiModel() })
            }
        } catch (e: Exception) {
            ContentState.Error(e.message ?: AppErrors.unknownError)
        }
}
