package com.example.crocusoft_mova.presentation.fill_profile

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FillProfileViewModel: ViewModel() {

    private val _state = MutableStateFlow(FillProfileContract.State())

    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<FillProfileContract.Effect>()

    val effect = _effect.asSharedFlow()

    fun onIntent(intent: FillProfileContract.Intent){
        when(intent){
            is FillProfileContract.Intent.SetEmail -> {
                viewModelScope.launch {
                    _state.emit(_state.value.copy(email = intent.email))
                }
            }
            is FillProfileContract.Intent.SetFullName -> {
                viewModelScope.launch {
                    _state.emit(_state.value.copy(fullName = intent.fullName))
                }
            }
            is FillProfileContract.Intent.SetNickName -> {
                viewModelScope.launch {
                    _state.emit(_state.value.copy(email = intent.nickName))
                }
            }

            is FillProfileContract.Intent.SetGender -> {
                viewModelScope.launch {
                    _state.emit(_state.value.copy(gender = intent.gender))
                }
            }
            is FillProfileContract.Intent.SetPhoneNumber ->{
                viewModelScope.launch {
                    _state.emit(_state.value.copy(phoneNumber = intent.phoneNumber))
                }
            }

            is FillProfileContract.Intent.SetProfile -> {
                viewModelScope.launch {
                    _state.emit(_state.value.copy(imgUri = intent.uri))
                }
            }

            FillProfileContract.Intent.Submit -> {
                viewModelScope.launch {
                    _effect.emit(FillProfileContract.Effect.NavigatePin)
                }
            }

            FillProfileContract.Intent.Skip -> {
                viewModelScope.launch {
                    _effect.emit(FillProfileContract.Effect.NavigatePin)
                }
            }
        }
    }
}