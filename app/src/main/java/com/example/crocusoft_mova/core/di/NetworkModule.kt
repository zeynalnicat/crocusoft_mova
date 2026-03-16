package com.example.crocusoft_mova.core.di

import com.example.crocusoft_mova.BuildConfig
import com.example.crocusoft_mova.data.service.remote.ApiService
import com.example.crocusoft_mova.data.service.remote.KtorService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideHttp(): HttpClient = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                coerceInputValues = true
                encodeDefaults = true
            })
        }
        install(Logging) {
            level = LogLevel.ALL
        }
    }

    @Singleton
    @Provides
    fun provideApiService(
        httpClient: HttpClient,
        @Named("api_key") apiKey: String
    ): ApiService = KtorService(httpClient, apiKey)

    @Provides
    @Named("api_key")
    fun provideApiKey(): String {
        return BuildConfig.API_KEY
    }
}
