package com.example.crocusoft_mova.presentation.auth.create_new_pin

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crocusoft_mova.core.ContentState
import com.example.crocusoft_mova.domain.usecases.CreatePinUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.lang.Character.digit


@HiltViewModel
class CreateNewPinViewModel @Inject constructor(private val createPinUseCase: CreatePinUseCase) :
    ViewModel() {

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

                Log.i("pin", state.value.pin.toString())
            }

            CreateNewPinContract.Intent.Submit -> {
                createPin()
            }
        }

    }

    private fun createPin() {
        viewModelScope.launch {
            when (val res = createPinUseCase(_state.value.pin.joinToString(separator = ""))) {
                is ContentState.Error<*> -> {
                    _effect.emit(CreateNewPinContract.Effect.ShowError(res.message))
                }

                is ContentState.Success<*> -> {
                    _effect.emit(CreateNewPinContract.Effect.NavigateHome)
                }
            }


        }
    }

}