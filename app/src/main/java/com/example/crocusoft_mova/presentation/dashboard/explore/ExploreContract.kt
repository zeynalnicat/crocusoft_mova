package com.example.crocusoft_mova.presentation.dashboard.explore

sealed interface ExploreContract {

    sealed interface Intent {
        data class SetQuery(val query: String) : Intent
    }

    data class State(
        val searchQuery: String = ""
    )
}