package com.example.crocusoft_mova.presentation.auth.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crocusoft_mova.core.ContentState
import com.example.crocusoft_mova.domain.usecases.SignInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


@HiltViewModel
class SignInViewModel @Inject constructor(private val signInUseCase: SignInUseCase) : ViewModel() {

    private val _state = MutableStateFlow(SignInContract.State())

    private val _effect = MutableSharedFlow<SignInContract.UiEffect>()

    val effect = _effect.asSharedFlow()

    val state = _state.asStateFlow()


    fun onIntent(intent: SignInContract.Intent) {
        when (intent) {
            is SignInContract.Intent.SetEmail -> _state.update { it.copy(email = intent.email) }


            is SignInContract.Intent.SetPassword -> _state.update { it.copy(password = intent.password) }


            SignInContract.Intent.Submit ->
                signIn()


            is SignInContract.Intent.SetChecked -> {
                _state.update { it.copy(checked = intent.checked) }

            }
        }
    }

    private fun signIn() {
        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            when (val res = signInUseCase(_state.value.email, _state.value.password)) {
                is ContentState.Error<*> -> {
                    _state.update { it.copy(isLoading = false) }
                    _effect.emit(SignInContract.UiEffect.ShowError(res.message))

                }

                is ContentState.Success<*> -> {
                    _state.update { it.copy(isLoading = false) }
                    _effect.emit(SignInContract.UiEffect.NavigateToChoose)
                }
            }
        }
    }


}