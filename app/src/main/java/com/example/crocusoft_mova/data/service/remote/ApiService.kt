package com.example.crocusoft_mova.data.service.remote

import com.example.crocusoft_mova.data.service.remote.model.MovieDetailModel
import com.example.crocusoft_mova.data.service.remote.model.ResponseModel
import com.example.crocusoft_mova.data.service.remote.model.VideoResponseModel

interface ApiService {


    suspend fun fetchDiscoverMovies(): ResponseModel

    suspend fun fetchDiscoverTv(): ResponseModel

    suspend fun fetchSimilarMovies(movieId : Int): ResponseModel

    suspend fun fetchMoviesVideos(movieId : Int): VideoResponseModel


    suspend fun searchMovie(query: String): ResponseModel

    suspend fun fetchTrending(): ResponseModel

    suspend fun fetchMovieDetail(movieId: Int): MovieDetailModel

    suspend fun fetchTopRatedMovies() : ResponseModel

    suspend fun fetchUpcomingMovies() : ResponseModel


}