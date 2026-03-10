package com.example.crocusoft_mova.presentation.auth.create_new_pin

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.lang.Character.digit

class CreateNewPinViewModel : ViewModel() {

    private val _state = MutableStateFlow(CreateNewPinContract.State())

    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<CreateNewPinContract.Effect>()

    val effect = _effect.asSharedFlow()

    fun onIntent(intent: CreateNewPinContract.Intent) {
        when (intent) {
            is CreateNewPinContract.Intent.SetPin -> {
                viewModelScope.launch {
                    val newPin = _state.value.pin.toMutableList()
                    newPin[intent.index] = intent.pin
                    _state.update { it.copy(pin = newPin) }
                }

                Log.i("pin",state.value.pin.toString())
            }

            CreateNewPinContract.Intent.Submit -> {
                   viewModelScope.launch {
                       _effect.emit(CreateNewPinContract.Effect.NavigateHome)
                   }
            }
        }

    }

}