package com.example.crocusoft_mova.presentation.choose_interest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.crocusoft_mova.domain.tag.TagEntity
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ChooseInterestViewModel: ViewModel()
{

    private val _state = MutableStateFlow(ChooseInterestContract.State())

    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<ChooseInterestContract.Effect>()

    val effect = _effect


    fun onIntent(intent: ChooseInterestContract.Intent){
        when(intent){
            is ChooseInterestContract.Intent.ToggleTag -> toggleTag(intent.tagEntity)
            ChooseInterestContract.Intent.FetchTags -> fetchTags()
            ChooseInterestContract.Intent.Continue -> {
                viewModelScope.launch {
                    _effect.emit(ChooseInterestContract.Effect.NavigateFillProfile)
                }
            }
        }
    }

    private fun toggleTag(tagEntity: TagEntity){

        if(_state.value.selectedTags.contains(tagEntity)){
            viewModelScope.launch {
                _state.emit(_state.value.copy(selectedTags = _state.value.selectedTags - tagEntity))
            }
        }else{
            viewModelScope.launch {
                _state.emit(_state.value.copy(selectedTags = _state.value.selectedTags + tagEntity))
            }
        }

    }

    private fun fetchTags(){
        viewModelScope.launch {
            val tags = (1..20).map{
                TagEntity(
                    id = it.toString(),
                    name = "Tag $it"
                )
            }
           _state.emit(_state.value.copy(tags = tags))
        }
    }
}