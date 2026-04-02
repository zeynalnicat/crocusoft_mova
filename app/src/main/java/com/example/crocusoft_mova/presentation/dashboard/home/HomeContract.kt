package com.example.crocusoft_mova.presentation.dashboard.home

import com.example.crocusoft_mova.domain.models.MovieUiModel

sealed interface HomeContract {


    sealed interface Intent {
        object FetchDiscoverMovies : Intent
        object FetchUpcomingMovies : Intent
        object FetchTopRatedMovies : Intent
    }

    sealed interface Effect{
        data class ShowError(val message:String): Effect
    }


    data class State(
        val discoverMovies: List<MovieUiModel> = emptyList(),
        val upcomingMovies : List<MovieUiModel> = emptyList(),
        val topRatedMovies : List<MovieUiModel> = emptyList(),
        val isDiscoverLoading: Boolean = false,
        val isUpcomingLoading: Boolean = false,
        val isTopRatedLoading: Boolean = false
    )

}
