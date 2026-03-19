package com.example.crocusoft_mova.domain.repository

import com.example.crocusoft_mova.core.ContentState
import com.example.crocusoft_mova.domain.models.MovieUiModel

interface MyListRepository {

    suspend fun fetchMyList(): ContentState<List<MovieUiModel>>
}