package com.example.crocusoft_mova.presentation.dashboard.movie_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crocusoft_mova.core.ContentState
import com.example.crocusoft_mova.domain.usecases.FetchMovieDetailUseCase
import com.example.crocusoft_mova.domain.usecases.FetchSimilarUseCase
import com.example.crocusoft_mova.domain.usecases.FetchVideosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val fetchMovieDetailUseCase: FetchMovieDetailUseCase,
    private val fetchSimilarUseCase: FetchSimilarUseCase,
    private val fetchVideosUseCase: FetchVideosUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(MovieDetailContract.State())
    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<MovieDetailContract.Effect>()
    val effect = _effect.asSharedFlow()

    fun onIntent(intent: MovieDetailContract.Intent) {
        when (intent) {
            is MovieDetailContract.Intent.FetchMovieDetail -> {
                fetchAllData(intent.movieId)
            }
            is MovieDetailContract.Intent.OnTabSelected -> {
                _state.update { it.copy(selectedTab = intent.index) }
            }
        }
    }

    private fun fetchAllData(movieId: Int) {
        fetchMovieDetail(movieId)
        fetchSimilarMovies(movieId)
        fetchMovieVideos(movieId)
    }

    private fun fetchMovieDetail(movieId: Int) {
        viewModelScope.launch {
            when (val res = fetchMovieDetailUseCase(movieId)) {
                is ContentState.Success -> {
                    _state.update { it.copy(movieDetail = res.data) }
                }
                is ContentState.Error -> {
                    _effect.emit(MovieDetailContract.Effect.ShowError(res.message))
                }
                //else -> Unit
            }
        }
    }

    private fun fetchSimilarMovies(movieId: Int) {
        viewModelScope.launch {
            when (val res = fetchSimilarUseCase(movieId)) {
                is ContentState.Success -> {
                    _state.update { it.copy(similarMovies = res.data) }
                }
                is ContentState.Error -> {
                    _effect.emit(MovieDetailContract.Effect.ShowError(res.message))
                }
                //else -> Unit
            }
        }
    }

    private fun fetchMovieVideos(movieId: Int) {
        viewModelScope.launch {
            when (val res = fetchVideosUseCase(movieId)) {
                is ContentState.Success -> {
                    _state.update { it.copy(trailers = res.data) }
                }
                is ContentState.Error -> {
                    _effect.emit(MovieDetailContract.Effect.ShowError(res.message))
                }
                //else -> Unit
            }
        }
    }
}