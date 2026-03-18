package com.example.crocusoft_mova.domain.repository

import com.example.crocusoft_mova.core.ContentState
import com.example.crocusoft_mova.domain.models.MovieUiModel

interface HomeRepository {

    suspend fun fetchDiscoverMovies(): ContentState<List<MovieUiModel>>

    suspend fun fetchDiscoverTv(): ContentState<List<MovieUiModel>>
}