package com.example.crocusoft_mova.presentation.auth.choose_interest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.crocusoft_mova.core.ContentState
import com.example.crocusoft_mova.domain.tag.TagEntity
import com.example.crocusoft_mova.domain.usecases.CheckIfTagsExistUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


@HiltViewModel
class ChooseInterestViewModel @Inject constructor(
    private val checkIfTagsExistUseCase: CheckIfTagsExistUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(ChooseInterestContract.State())

    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<ChooseInterestContract.Effect>()

    val effect = _effect


    init {
        fetchTags()
        checkIfExist()
    }


    fun onIntent(intent: ChooseInterestContract.Intent) {
        when (intent) {
            is ChooseInterestContract.Intent.ToggleTag -> toggleTag(intent.tagEntity)

            ChooseInterestContract.Intent.Continue -> {
                viewModelScope.launch {
                    _effect.emit(ChooseInterestContract.Effect.NavigateFillProfile)
                }
            }
        }
    }


    private fun checkIfExist() {
        viewModelScope.launch {
            when (val res = checkIfTagsExistUseCase()) {
                is ContentState.Error<*> -> {
                    _effect.emit(ChooseInterestContract.Effect.ShowError(res.message))
                }

                is ContentState.Success<Boolean> -> {
                    if (res.data) {
                        _effect.emit(ChooseInterestContract.Effect.NavigateHome)
                    }
                }
            }
        }
    }

    private fun toggleTag(tagEntity: TagEntity) {

        if (_state.value.selectedTags.contains(tagEntity)) {
            viewModelScope.launch {
                _state.emit(_state.value.copy(selectedTags = _state.value.selectedTags - tagEntity))
            }
        } else {
            viewModelScope.launch {
                _state.emit(_state.value.copy(selectedTags = _state.value.selectedTags + tagEntity))
            }
        }

    }

    private fun fetchTags() {
        viewModelScope.launch {
            val tags = (1..20).map {
                TagEntity(
                    id = it.toString(),
                    name = "Tag $it"
                )
            }
            _state.emit(_state.value.copy(tags = tags))
        }
    }
}