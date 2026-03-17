package com.example.crocusoft_mova.presentation.dashboard.profile

import androidx.lifecycle.ViewModel
import com.example.crocusoft_mova.domain.models.ProfileUiModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ProfileViewModel: ViewModel() {

    private val _state = MutableStateFlow(ProfileContract.State())
    val state = _state.asStateFlow()


    init {
        loadProfile()
    }

    fun onIntent(intent: ProfileContract.Intent){
        when(intent){
            ProfileContract.Intent.LoadProfile -> {
                loadProfile()
            }
        }
    }

    private fun loadProfile(){
        _state.update { it.copy(profile = ProfileUiModel.mock) }
    }
}
