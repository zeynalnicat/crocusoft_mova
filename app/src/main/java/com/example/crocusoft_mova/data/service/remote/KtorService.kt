package com.example.crocusoft_mova.data.service.remote

import com.example.crocusoft_mova.core.constants.ApiConstants
import com.example.crocusoft_mova.data.service.remote.model.ResponseModel
import io.github.cdimascio.dotenv.dotenv
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.HttpHeaders
import io.ktor.http.headers
import javax.inject.Inject
import javax.inject.Named

class KtorService @Inject constructor(
    private val httpClient: HttpClient,
    @Named("api_key") private val api_key: String
) : ApiService {

    override suspend fun fetchDiscoverMovies(): ResponseModel {
        return httpClient
            .get(ApiConstants.DISCOVER) {
                headers {
                    append(HttpHeaders.Accept, "application/json")
                    append(HttpHeaders.Authorization, "Bearer $api_key")
                }
            }
            .body()
    }

    override suspend fun fetchDiscoverTv(): ResponseModel {
        return httpClient
            .get(ApiConstants.DISCOVER_TV) {
                headers {
                    append(HttpHeaders.Accept, "application/json")
                    append(HttpHeaders.Authorization, "Bearer $api_key")
                }
            }
            .body()
    }
}
