package com.example.crocusoft_mova.data.mappers

import com.example.crocusoft_mova.data.service.remote.model.MovieDetailModel
import com.example.crocusoft_mova.data.service.remote.model.MovieModel
import com.example.crocusoft_mova.domain.models.MovieDetailUiModel
import com.example.crocusoft_mova.domain.models.MovieUiModel
import com.example.crocusoft_mova.domain.models.ProductionCompany


fun MovieModel.toUiModel(): MovieUiModel = MovieUiModel(
    id = id,
    title = title ?: "",
    vote_average = vote_average ?: 0.0,
    release_date = release_date ?: "",
    description = original_title ?: "",
    image = poster_path ?: ""
)

fun MovieDetailModel.toUiModel(): MovieDetailUiModel = MovieDetailUiModel(
    id = id ?: 0,
    title = title ?: "",
    release_date = release_date ?: "",
    description = overview ?: "",
    vote_average = vote_average ?: 0.0,
    image = poster_path ?: "",
    genres = genres?.map { it.name ?: "" } ?: emptyList(),
    production_companies = production_companies?.map {
        ProductionCompany(
            it.name ?: "",
            it.logo_path ?: ""
        )
    } ?: emptyList(),
    language = original_language ?: ""


)
