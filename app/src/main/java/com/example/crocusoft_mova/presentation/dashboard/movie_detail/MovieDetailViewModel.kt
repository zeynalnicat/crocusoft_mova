package com.example.crocusoft_mova.presentation.dashboard.movie_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crocusoft_mova.core.ContentState
import com.example.crocusoft_mova.domain.models.MovieDetailUiModel
import com.example.crocusoft_mova.domain.models.MovieUiModel
import com.example.crocusoft_mova.domain.usecases.AddToMyListUseCase
import com.example.crocusoft_mova.domain.usecases.FetchMovieDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val fetchMovieDetailUseCase: FetchMovieDetailUseCase,
    private val addToMyListUseCase: AddToMyListUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(MovieDetailContract.State())

    private val _effect = MutableSharedFlow<MovieDetailContract.Effect>()

    val state = _state.asStateFlow()

    fun onIntent(intent: MovieDetailContract.Intent) {
        when (intent) {
            is MovieDetailContract.Intent.FetchMovieDetail -> fetchMovieDetail(intent.movieId)
            MovieDetailContract.Intent.AddToMyList -> addToMyList()
        }
    }


    private fun addToMyList() {
        viewModelScope.launch {
            when (val res = addToMyListUseCase(_state.value.movieDetail)) {
                is ContentState.Error<*> -> {
                    _effect.emit(MovieDetailContract.Effect.ShowError((res.message)))
                }

                is ContentState.Success<*> -> {
                    _state.update { movie -> movie.copy(movieDetail = movie.movieDetail.copy(isAdded = !movie.movieDetail.isAdded)) }
                }
            }
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