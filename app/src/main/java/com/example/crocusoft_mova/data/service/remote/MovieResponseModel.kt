package com.example.crocusoft_mova.data.service.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ResponseModel(

    @SerialName("page")
    val page: Int,
    @SerialName("results")
    val results: List<MovieModel>,
    @SerialName("total_pages")
    val total_pages: Int,
    @SerialName("total_results")
    val total_results: Int
)