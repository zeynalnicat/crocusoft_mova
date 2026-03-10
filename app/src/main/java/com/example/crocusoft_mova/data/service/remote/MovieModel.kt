package com.example.crocusoft_mova.data.service.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class MovieModel(

    @SerialName("adult")
    val adult: Boolean,
    @SerialName("genre_ids")
    val genre_ids: List<Int>,
    @SerialName("id")
    val id: Int,
    @SerialName("original_language")
    val original_language: String,
    @SerialName("original_title")
    val original_title: String,
    @SerialName("overview")
    val overview: String,
    @SerialName("popularity")
    val popularity: Double,
    @SerialName("poster_path")
    val poster_path: String,
    @SerialName("release_date")
    val release_date: String,
    @SerialName("title")
    val title: String,
    @SerialName("video")
    val video: Boolean,
    @SerialName("vote_average")
    val vote_average: Double,
)