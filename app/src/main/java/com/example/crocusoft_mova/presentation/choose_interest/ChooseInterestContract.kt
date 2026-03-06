package com.example.crocusoft_mova.presentation.choose_interest

import com.example.crocusoft_mova.domain.tag.TagEntity

sealed class ChooseInterestContract {

    sealed interface Intent{
        data class ToggleTag(val tagEntity: TagEntity):Intent

        data object FetchTags: Intent

        data object Continue: Intent
    }

    sealed interface Effect {
        data object NavigateFillProfile: Effect
    }

    data class State(
        val tags: List<TagEntity> = emptyList(),
        val selectedTags: Set<TagEntity> = emptySet()
    )
}