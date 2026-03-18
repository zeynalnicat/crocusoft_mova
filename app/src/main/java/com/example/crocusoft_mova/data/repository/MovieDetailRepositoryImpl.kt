package com.example.crocusoft_mova.data.repository

import com.example.crocusoft_mova.core.ContentState
import com.example.crocusoft_mova.core.constants.AppErrors
import com.example.crocusoft_mova.data.mappers.toUiModel
import com.example.crocusoft_mova.data.service.remote.ApiService
import com.example.crocusoft_mova.data.service.remote.model.MovieDetailModel
import com.example.crocusoft_mova.domain.models.MovieDetailUiModel
import com.example.crocusoft_mova.domain.repository.MovieDetailRepository
import jakarta.inject.Inject

class MovieDetailRepositoryImpl @Inject constructor(private val apiService: ApiService) :
    MovieDetailRepository {
    override suspend fun getMovieDetail(movieId: Int): ContentState<MovieDetailUiModel> = try {
        val res = apiService.fetchMovieDetail(movieId)
        ContentState.Success(res.toUiModel())


    } catch (e: Exception) {
        ContentState.Error(e.message ?: AppErrors.unknownError)
    }

}