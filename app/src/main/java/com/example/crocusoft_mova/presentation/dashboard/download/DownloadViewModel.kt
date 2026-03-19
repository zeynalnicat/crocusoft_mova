package com.example.crocusoft_mova.presentation.dashboard.download

import androidx.lifecycle.ViewModel
import com.example.crocusoft_mova.core.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class DownloadViewModel : BaseViewModel<DownloadContract.Intent, DownloadContract.State>() {

    private val _state = MutableStateFlow(DownloadContract.State())
    override val state = _state.asStateFlow()

    override fun onIntent(intent: DownloadContract.Intent) {
        when (intent) {
            DownloadContract.Intent.LoadMovies -> {}
        }
    }
}