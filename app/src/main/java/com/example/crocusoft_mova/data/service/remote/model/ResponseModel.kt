package com.example.crocusoft_mova.data.service.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseModel(
    @SerialName("page")
    val page: Int? = 0,
    @SerialName("results")
    val results: List<MovieModel>? = emptyList(),
    @SerialName("total_pages")
    val totalPages: Int? = 0,
    @SerialName("total_results")
    val totalResults: Int? = 0
)
