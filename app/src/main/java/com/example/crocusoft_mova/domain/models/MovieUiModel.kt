package com.example.crocusoft_mova.domain.models

data class MovieUiModel(
    val id:Int,
    val title:String,
    val release_date: String,
    val description: String,
    val vote_average: Double,
    val image:String,
)