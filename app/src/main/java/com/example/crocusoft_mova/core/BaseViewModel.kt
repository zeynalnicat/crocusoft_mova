package com.example.crocusoft_mova.core

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel<T, N:CoreState> : ViewModel() {

    abstract val state: StateFlow<N>

    abstract fun onIntent(intent: T)
}