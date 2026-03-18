package com.example.crocusoft_mova.domain.repository

import com.example.crocusoft_mova.core.ContentState
import com.example.crocusoft_mova.domain.models.MovieDetailUiModel

interface MovieDetailRepository {

    suspend fun getMovieDetail(movieId: Int): ContentState<MovieDetailUiModel>
}