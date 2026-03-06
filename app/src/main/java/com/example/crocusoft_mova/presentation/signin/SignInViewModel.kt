package com.example.crocusoft_mova.presentation.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crocusoft_mova.presentation.signup.SignUpContract
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SignInViewModel: ViewModel() {

    private val _state = MutableStateFlow(SignInContract.State())

    val state = _state.asStateFlow()


    fun onIntent(intent: SignInContract.Intent) {
        when (intent) {
            is SignInContract.Intent.SetEmail -> (
                    viewModelScope.launch {
                        _state.emit(_state.value.copy(email = intent.email))
                    } )

            is SignInContract.Intent.SetPassword -> {
                viewModelScope.launch {
                    _state.emit(_state.value.copy(password = intent.password))
                }
            }
            SignInContract.Intent.Submit -> {
                viewModelScope.launch {
                    _state.emit(_state.value.copy(isLoading = !_state.value.isLoading))


                }


            }
            is SignInContract.Intent.SetChecked -> {
                viewModelScope.launch {
                    _state.emit(_state.value.copy(checked = intent.checked))
                }
            }
        }
    }

}