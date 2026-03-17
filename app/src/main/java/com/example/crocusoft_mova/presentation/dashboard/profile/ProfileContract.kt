package com.example.crocusoft_mova.presentation.dashboard.profile

import com.example.crocusoft_mova.domain.models.ProfileUiModel

sealed interface ProfileContract {

    sealed interface Intent {
        data object LoadProfile : Intent

    }

    data class State(
        val profile: ProfileUiModel = ProfileUiModel.empty
    )
}