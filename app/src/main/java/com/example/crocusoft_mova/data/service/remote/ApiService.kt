package com.example.crocusoft_mova.data.service.remote

import com.example.crocusoft_mova.data.service.remote.model.MovieDetailModel
import com.example.crocusoft_mova.data.service.remote.model.ResponseModel

interface ApiService {


    suspend fun fetchDiscoverMovies(): ResponseModel

    suspend fun fetchDiscoverTv(): ResponseModel

    suspend fun searchMovie(query: String): ResponseModel

    suspend fun fetchTrending(): ResponseModel

    suspend fun fetchMovieDetail(movieId: Int): MovieDetailModel


}