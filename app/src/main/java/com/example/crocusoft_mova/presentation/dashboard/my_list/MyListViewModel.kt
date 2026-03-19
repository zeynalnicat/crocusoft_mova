package com.example.crocusoft_mova.presentation.dashboard.my_list

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.crocusoft_mova.core.BaseViewModel
import com.example.crocusoft_mova.core.ContentState
import com.example.crocusoft_mova.domain.models.MovieUiModel
import com.example.crocusoft_mova.domain.usecases.FetchMyListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


@HiltViewModel
class MyListViewModel @Inject constructor(
    private val fetchMyListUseCase: FetchMyListUseCase
) : BaseViewModel<MyListContract.Intent, MyListContract.State>() {


    init {
        fetchMyList()
    }

    private val _state = MutableStateFlow(MyListContract.State())
    override val state = _state.asStateFlow()

    override fun onIntent(intent: MyListContract.Intent) {

    }

    private fun fetchMyList() {
        viewModelScope.launch {
            when (val res = fetchMyListUseCase()) {

                is ContentState.Error<*> -> TODO()
                is ContentState.Success<List<MovieUiModel>> -> {
                    _state.value = _state.value.copy(movies = res.data)
                }
            }
        }
    }


}