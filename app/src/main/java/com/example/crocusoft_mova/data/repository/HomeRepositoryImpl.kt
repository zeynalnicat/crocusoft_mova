package com.example.crocusoft_mova.data.repository

import com.example.crocusoft_mova.core.ContentState
import com.example.crocusoft_mova.core.constants.AppErrors
import com.example.crocusoft_mova.data.mappers.toUiModel
import com.example.crocusoft_mova.data.service.remote.ApiService
import com.example.crocusoft_mova.domain.models.MovieUiModel
import com.example.crocusoft_mova.domain.repository.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(private val apiService: ApiService) : HomeRepository {
    override suspend fun fetchDiscoverMovies(): ContentState<List<MovieUiModel>> =
        try {
            val res = apiService.fetchDiscoverMovies()
            val results = res.results
            if (results.isNullOrEmpty()) {
                ContentState.Error(AppErrors.noMovies)
            } else {
                ContentState.Success(results.map { it.toUiModel() })
            }
        } catch (e: Exception) {
            ContentState.Error(e.message ?: AppErrors.unknownError)
        }

    override suspend fun fetchDiscoverTv(): ContentState<List<MovieUiModel>> =
        try {
            val res = apiService.fetchDiscoverTv()
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
