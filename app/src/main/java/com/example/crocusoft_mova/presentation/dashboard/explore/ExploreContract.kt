package com.example.crocusoft_mova.presentation.dashboard.explore

import com.example.crocusoft_mova.domain.models.MovieUiModel

sealed interface ExploreContract {

    sealed interface Intent {
        data class SetQuery(val query: String) : Intent
    }

    data class State(
        val searchQuery: String = "",
        val movies: List<MovieUiModel> = emptyList()

    )
}