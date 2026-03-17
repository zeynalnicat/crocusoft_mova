package com.example.crocusoft_mova.presentation.dashboard.explore

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ExploreViewModel : ViewModel() {

    private val _state = MutableStateFlow(ExploreContract.State())
    val state = _state.asStateFlow()

    fun onIntent(intent: ExploreContract.Intent) {
        when (intent) {
            is ExploreContract.Intent.SetQuery -> {
                _state.update { it.copy(searchQuery = intent.query) }
            }
        }

    }
}