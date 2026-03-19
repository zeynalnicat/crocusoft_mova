package com.example.crocusoft_mova.presentation.dashboard.home

import com.example.crocusoft_mova.domain.models.MovieUiModel

sealed interface HomeContract {


    sealed interface Intent {
        object FetchDiscoverMovies : Intent
        object FetchUpcomingMovies : Intent
    }

    sealed interface Effect{
        data class ShowError(val message:String): Effect
    }


    data class State(
        val discoverMovies: List<MovieUiModel> = emptyList(),
        val upcomingMovies : List<MovieUiModel> = emptyList()
    )

}
