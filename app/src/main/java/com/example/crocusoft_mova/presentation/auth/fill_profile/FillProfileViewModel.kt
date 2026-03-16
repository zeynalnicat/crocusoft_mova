package com.example.crocusoft_mova.presentation.auth.fill_profile

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.crocusoft_mova.core.ContentState
import com.example.crocusoft_mova.domain.usecases.FillProfileUseCase
import com.example.crocusoft_mova.domain.usecases.FillProfileUseCase.*
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


@HiltViewModel
class FillProfileViewModel @Inject constructor(
    private val fillProfileUseCase: FillProfileUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(FillProfileContract.State())

    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<FillProfileContract.Effect>()

    val effect = _effect.asSharedFlow()

    fun onIntent(intent: FillProfileContract.Intent) {
        when (intent) {
            is FillProfileContract.Intent.SetEmail -> {
                _state.update { it.copy(email = intent.email) }

            }

            is FillProfileContract.Intent.SetFullName -> {
                _state.update { it.copy(fullName = intent.fullName) }

            }

            is FillProfileContract.Intent.SetNickName -> {

                _state.update { it.copy(nickName = intent.nickName) }

            }

            is FillProfileContract.Intent.SetGender -> {

                _state.update { it.copy(gender = intent.gender) }

            }

            is FillProfileContract.Intent.SetPhoneNumber -> {

                _state.update { it.copy(phoneNumber = intent.phoneNumber) }

            }

            is FillProfileContract.Intent.SetProfile -> {

                _state.update { it.copy(imgUri = intent.uri) }

            }

            FillProfileContract.Intent.Submit -> submit()

            FillProfileContract.Intent.Skip -> {
                viewModelScope.launch {
                    _effect.emit(FillProfileContract.Effect.NavigatePin)
                }
            }
        }
    }

    private fun submit() {
        viewModelScope.launch {
            when (val res = fillProfileUseCase.invoke(
                Params(
                    fullName = state.value.fullName,
                    gender = state.value.gender,
                    nickname = state.value.nickName,
                    phoneNumber = state.value.phoneNumber,
                    profileUri = ""
                )
            )) {
                is ContentState.Error<*> -> _effect.emit(

                    FillProfileContract.Effect.ShowError(
                        res.message
                    )

                )

                is ContentState.Success<*> -> {
                    _effect.emit(FillProfileContract.Effect.NavigatePin)
                }
            }

        }
    }
}