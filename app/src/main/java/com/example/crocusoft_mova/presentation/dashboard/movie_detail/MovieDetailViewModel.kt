package com.example.crocusoft_mova.presentation.dashboard.movie_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crocusoft_mova.core.ContentState
import com.example.crocusoft_mova.domain.models.MovieDetailUiModel
import com.example.crocusoft_mova.domain.usecases.FetchMovieDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val fetchMovieDetailUseCase: FetchMovieDetailUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(MovieDetailContract.State())

    val state = _state.asStateFlow()

    fun onIntent(intent: MovieDetailContract.Intent) {
        when (intent) {
            is MovieDetailContract.Intent.FetchMovieDetail -> fetchMovieDetail(intent.movieId)
        }
    }

    private fun fetchMovieDetail(movieId: Int) {
        viewModelScope.launch {
            when (val res = fetchMovieDetailUseCase(movieId)) {

                is ContentState.Error<*> -> {}

                is ContentState.Success<MovieDetailUiModel> -> {
                    _state.update { it.copy(movieDetail = res.data) }
                }
            }
        }
    }
}