package com.example.crocusoft_mova.presentation.dashboard.explore

import com.example.crocusoft_mova.domain.models.MovieUiModel

sealed interface ExploreContract {

    sealed interface Intent {
        data class SetQuery(val query: String) : Intent

        data object Search : Intent

    }

    sealed interface Effect {
        data class ShowError(val message:String): Effect

    }

    data class State(
        val searchQuery: String = "",
        val movies: List<MovieUiModel> = emptyList()

    )
}