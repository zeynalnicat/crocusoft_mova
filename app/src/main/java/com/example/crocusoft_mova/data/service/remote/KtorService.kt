package com.example.crocusoft_mova.data.service.remote

import com.example.crocusoft_mova.core.constants.ApiConstants
import com.example.crocusoft_mova.data.service.remote.model.MovieDetailModel
import com.example.crocusoft_mova.data.service.remote.model.ResponseModel
import com.example.crocusoft_mova.data.service.remote.model.VideoResponseModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.http.HttpHeaders
import javax.inject.Inject
import javax.inject.Named

class KtorService @Inject constructor(
    private val httpClient: HttpClient,
    @Named("api_key") private val api_key: String
) : ApiService {

    override suspend fun fetchDiscoverMovies(): ResponseModel {
        return httpClient
            .get(ApiConstants.DISCOVER) {
                header(HttpHeaders.Authorization, "Bearer $api_key")
                header(HttpHeaders.Accept, "application/json")
                parameter("page", 1)
            }
            .body()
    }

    override suspend fun fetchDiscoverTv(): ResponseModel {
        return httpClient
            .get(ApiConstants.DISCOVER_TV) {
                header(HttpHeaders.Authorization, "Bearer $api_key")
                header(HttpHeaders.Accept, "application/json")
                parameter("page", 1)
            }
            .body()
    }

    override suspend fun fetchSimilarMovies(movieId: Int): ResponseModel {
        return httpClient.get(ApiConstants.MOVIE_SIMILAR.replace("{movie_id}", movieId.toString())){
            header(HttpHeaders.Authorization, "Bearer $api_key")
            header(HttpHeaders.Accept, "application/json")
        }.body()
    }

    override suspend fun fetchMoviesVideos(movieId: Int): VideoResponseModel {
        return httpClient.get(ApiConstants.MOVIE_VIDEOS.replace("{movie_id}", movieId.toString())){
            header(HttpHeaders.Authorization, "Bearer $api_key")
            header(HttpHeaders.Accept, "application/json")
        }.body()
    }

    override suspend fun searchMovie(query: String): ResponseModel {
        return httpClient.get(ApiConstants.SEARCH) {
            header(HttpHeaders.Authorization, "Bearer $api_key")
            header(HttpHeaders.Accept, "application/json")
            parameter("query", query)
            parameter("page", 1)
        }.body()
    }

    override suspend fun fetchTrending(): ResponseModel {
        return httpClient.get(ApiConstants.TRENDING) {
            header(HttpHeaders.Authorization, "Bearer $api_key")
            header(HttpHeaders.Accept, "application/json")
            parameter("page", 1)
        }.body()

    }

    override suspend fun fetchMovieDetail(movieId: Int): MovieDetailModel {
         return httpClient.get(ApiConstants.MOVIE_DETAIL.replace("{movie_id}", movieId.toString())){
             header(HttpHeaders.Authorization, "Bearer $api_key")
             header(HttpHeaders.Accept, "application/json")
         }.body()
    }

    override suspend fun fetchTopRatedMovies(): ResponseModel {
        return httpClient.get(ApiConstants.TOP_RATED) {
            header(HttpHeaders.Authorization, "Bearer $api_key")
            header(HttpHeaders.Accept, "application/json")
            parameter("page", 1)
        }.body()
    }

    override suspend fun fetchUpcomingMovies(): ResponseModel {
        return httpClient.get(ApiConstants.UPCOMING) {
            header(HttpHeaders.Authorization, "Bearer $api_key")
            header(HttpHeaders.Accept, "application/json")
            parameter("page", 1)
        }.body()
    }
}
