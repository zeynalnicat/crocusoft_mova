package com.example.crocusoft_mova.data.mappers

import com.example.crocusoft_mova.data.service.remote.model.MovieModel
import com.example.crocusoft_mova.domain.models.MovieUiModel


fun MovieModel.toUiModel(): MovieUiModel = MovieUiModel(
    id = id,
    title = title,
    vote_average = vote_average,
    release_date = release_date,
    description = original_title,
    image = poster_path
)