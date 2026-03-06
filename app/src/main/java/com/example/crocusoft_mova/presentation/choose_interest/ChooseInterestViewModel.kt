package com.example.crocusoft_mova.presentation.choose_interest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crocusoft_mova.domain.tag.TagEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ChooseInterestViewModel: ViewModel()
{

    private val _state = MutableStateFlow(ChooseInterestContract.State())

    val state = _state.asStateFlow()


    fun onIntent(intent: ChooseInterestContract.Intent){
        when(intent){
            is ChooseInterestContract.Intent.ToggleTag -> toggleTag(intent.tagEntity)
            ChooseInterestContract.Intent.FetchTags -> fetchTags()
        }
    }

    private fun toggleTag(tagEntity: TagEntity){

        viewModelScope.launch {
            if(tagEntity.isSelected){
                _state.emit(_state.value.copy(selectedTags = _state.value.selectedTags - tagEntity))
            }else{
                _state.emit(_state.value.copy(selectedTags = _state.value.selectedTags + tagEntity))
            }
        }
    }

    private fun fetchTags(){
        viewModelScope.launch {
            val tags = (1..20).map{
                TagEntity(
                    name = it.toString(),
                    isSelected = false
                )
            }
           _state.emit(_state.value.copy(tags = tags))
        }
    }
}