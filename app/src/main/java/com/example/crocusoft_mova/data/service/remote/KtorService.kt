package com.example.crocusoft_mova.data.service.remote

import com.example.crocusoft_mova.core.constants.ApiConstants
import com.example.crocusoft_mova.data.service.remote.model.DiscoverModel
import com.example.crocusoft_mova.data.service.remote.model.Genre
import com.example.crocusoft_mova.data.service.remote.model.GenreResponseModel
import com.example.crocusoft_mova.data.service.remote.model.MovieDetailModel
import com.example.crocusoft_mova.data.service.remote.model.MovieModel
import com.example.crocusoft_mova.data.service.remote.model.RegionModel
import com.example.crocusoft_mova.data.service.remote.model.ResponseModel
import com.example.crocusoft_mova.data.service.remote.model.TvModel
import com.example.crocusoft_mova.data.service.remote.model.VideoResponseModel
import com.example.crocusoft_mova.domain.models.GenreUiModel
import com.example.crocusoft_mova.presentation.dashboard.explore.util.MovieCategory
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

  /*  override suspend fun fetchDiscoverMovies(region: String?, genre: String?, time: String?, sort: String): ResponseModel<MovieModel> {
        return httpClient
            .get(ApiConstants.DISCOVER_MOVIE) {
                header(HttpHeaders.Authorization, "Bearer $api_key")
                header(HttpHeaders.Accept, "application/json")
                parameter("page", 1)
                parameter("with_genres", genre)
                parameter("region", region)
                parameter("primary_release_year", time)
                parameter("sort_by", sort)
            }
            .body()
    }

    override suspend fun fetchDiscoverTv(region: String?,genre: String?,time:String?,sort:String): ResponseModel<TvModel> {
        return httpClient
            .get(ApiConstants.DISCOVER_TV) {
                header(HttpHeaders.Authorization, "Bearer $api_key")
                header(HttpHeaders.Accept, "application/json")
                parameter("page", 1)
                parameter("with_genres", genre)
                parameter("region", region)
                parameter("primary_release_year", time)
                parameter("sort_by", sort)
            }
            .body()
    }*/

    override suspend fun fetchSimilarMovies(movieId: Int): ResponseModel<MovieModel> {
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

    override suspend fun fetchNowPlaying(): ResponseModel<MovieModel> {
        return httpClient.get(ApiConstants.NOW_PLAYING) {
            header(HttpHeaders.Authorization, "Bearer $api_key")
            header(HttpHeaders.Accept, "application/json")
            parameter("page", 1)
        }.body()
    }

    override suspend fun fetchDiscovers(category: String, region: String?, genre: String?, time: String?, sort: String): ResponseModel<DiscoverModel> {
        return httpClient
            .get(ApiConstants.DISCOVER.replace("{category}",category)) {
                header(HttpHeaders.Authorization, "Bearer $api_key")
                header(HttpHeaders.Accept, "application/json")
                parameter("page", 1)
                parameter("with_genres", genre)
                parameter("region", region)
                if(category == "movie"){
                    parameter("primary_release_year", time)
                }
                else{
                    parameter("first_air_date_year", time)
                }
                parameter("sort_by", sort)
            }
            .body()
    }

    override suspend fun fetchRegions(): List<RegionModel> {
        return httpClient.get(ApiConstants.REGIONS) {
            header(HttpHeaders.Authorization, "Bearer $api_key")
            header(HttpHeaders.Accept, "application/json")
            parameter("language","en-US")
        }.body()
    }
    override suspend fun fetchTvGenres(): GenreResponseModel {
        return httpClient.get(ApiConstants.GENRES_TV) {
            header(HttpHeaders.Authorization, "Bearer $api_key")
            header(HttpHeaders.Accept, "application/json")
            parameter("language","en")
        }.body()
    }
    override suspend fun fetchMovieGenres(): GenreResponseModel {
        return httpClient.get(ApiConstants.GENRES_MOVIE) {
            header(HttpHeaders.Authorization, "Bearer $api_key")
            header(HttpHeaders.Accept, "application/json")
            parameter("language","en")
        }.body()
    }


    override suspend fun searchMovie(query: String): ResponseModel<MovieModel> {
        return httpClient.get(ApiConstants.SEARCH) {
            header(HttpHeaders.Authorization, "Bearer $api_key")
            header(HttpHeaders.Accept, "application/json")
            parameter("query", query)
            parameter("page", 1)
        }.body()
    }

    override suspend fun fetchTrending(): ResponseModel<MovieModel> {
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

    override suspend fun fetchTopRatedMovies(): ResponseModel<MovieModel> {
        return httpClient.get(ApiConstants.TOP_RATED) {
            header(HttpHeaders.Authorization, "Bearer $api_key")
            header(HttpHeaders.Accept, "application/json")
            parameter("page", 1)
        }.body()
    }

    override suspend fun fetchUpcomingMovies(): ResponseModel<MovieModel> {
        return httpClient.get(ApiConstants.UPCOMING) {
            header(HttpHeaders.Authorization, "Bearer $api_key")
            header(HttpHeaders.Accept, "application/json")
            parameter("page", 1)
        }.body()
    }
}
