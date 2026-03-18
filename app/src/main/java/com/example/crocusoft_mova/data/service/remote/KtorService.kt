package com.example.crocusoft_mova.data.service.remote

import com.example.crocusoft_mova.core.constants.ApiConstants
import com.example.crocusoft_mova.data.service.remote.model.ResponseModel
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
}
