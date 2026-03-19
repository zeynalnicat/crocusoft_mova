package com.example.crocusoft_mova.presentation.dashboard.my_list

import com.example.crocusoft_mova.domain.models.MovieUiModel

sealed interface MyListContract {

    sealed interface Intent{

    }

    sealed interface Effect

    data class State(
        val movies:List<MovieUiModel> = emptyList()

    )
}