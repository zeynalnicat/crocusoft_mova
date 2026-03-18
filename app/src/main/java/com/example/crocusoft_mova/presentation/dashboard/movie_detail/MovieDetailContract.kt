package com.example.crocusoft_mova.presentation.dashboard.movie_detail

import com.example.crocusoft_mova.domain.models.MovieDetailUiModel

sealed interface MovieDetailContract {

    sealed interface Intent {
        data class FetchMovieDetail(val movieId:Int) : Intent
    }

    data class State(
        val movieDetail: MovieDetailUiModel = MovieDetailUiModel.empty,
    )
}