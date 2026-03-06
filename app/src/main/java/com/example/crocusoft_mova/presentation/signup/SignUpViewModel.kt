package com.example.crocusoft_mova.presentation.signup

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crocusoft_mova.presentation.signin.SignInContract
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SignUpViewModel : ViewModel() {

    private val _state = MutableStateFlow(SignUpContract.State())

    val state = _state.asStateFlow()


    private val _effect = MutableSharedFlow<SignUpContract.UiEffect>()

    val effect = _effect.asSharedFlow()


    fun onIntent(intent: SignUpContract.Intent) {
        when (intent) {
            is SignUpContract.Intent.SetEmail -> (
                    viewModelScope.launch {
                        _state.emit(_state.value.copy(email = intent.email))
                    } )

            is SignUpContract.Intent.SetPassword -> {
                viewModelScope.launch {
                    _state.emit(_state.value.copy(password = intent.password))
                }
            }
            SignUpContract.Intent.Submit -> {
                viewModelScope.launch {
                    _state.emit(_state.value.copy(isLoading = !_state.value.isLoading))
                    _effect.emit(SignUpContract.UiEffect.NavigateToChoose)

                }


            }
            is SignUpContract.Intent.SetChecked -> {
                viewModelScope.launch {
                    _state.emit(_state.value.copy(checked = intent.checked))
                }
            }
        }
    }

}

