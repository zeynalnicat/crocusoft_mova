package com.example.crocusoft_mova.presentation.dashboard.home


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crocusoft_mova.core.ContentState
import com.example.crocusoft_mova.domain.models.MovieUiModel
import com.example.crocusoft_mova.domain.usecases.FetchDiscoverMovieUseCase
import com.example.crocusoft_mova.domain.usecases.FetchDiscoverTVUseCase
import com.example.crocusoft_mova.domain.usecases.FetchNowPlayingMoviesUseCase
import com.example.crocusoft_mova.domain.usecases.FetchTopRatedUseCase
import com.example.crocusoft_mova.domain.usecases.FetchUpcomingMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchNowPlayingMoviesUseCase: FetchNowPlayingMoviesUseCase,
    private val fetchUpcomingMoviesUseCase: FetchUpcomingMoviesUseCase,
    private val fetchTopRatedUseCase: FetchTopRatedUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(HomeContract.State())
    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<HomeContract.Effect>()
    val effect = _effect.asSharedFlow()

    init {
        onIntent(HomeContract.Intent.FetchNowPlayingMovies)
        onIntent(HomeContract.Intent.FetchUpcomingMovies)
        onIntent(HomeContract.Intent.FetchTopRatedMovies)
    }

    fun onIntent(intent: HomeContract.Intent) {
        when (intent) {
            HomeContract.Intent.FetchNowPlayingMovies -> fetchNowPlayingMovies()
            HomeContract.Intent.FetchUpcomingMovies -> fetchUpcomingMovies()
            HomeContract.Intent.FetchTopRatedMovies -> fetchTopRatedMovies()
        }
    }

    private fun fetchNowPlayingMovies() {
        viewModelScope.launch {
            _state.update { it.copy(isNowPlayingLoading = true) }

            when (val res = fetchNowPlayingMoviesUseCase()) {
                is ContentState.Error<*> -> {
                    _state.update { it.copy(isNowPlayingLoading = false) }
                    _effect.emit(HomeContract.Effect.ShowError(res.message))
                }

                is ContentState.Success<List<MovieUiModel>> -> {
                    _state.update {
                        it.copy(nowPlayingMovies = res.data, isNowPlayingLoading = false)
                    }
                }
            }
        }
    }

    private fun fetchUpcomingMovies() {
        viewModelScope.launch {
            _state.update { it.copy(isUpcomingLoading = true) }

            when (val res = fetchUpcomingMoviesUseCase()) {
                is ContentState.Error<*> -> {
                    _state.update { it.copy(isUpcomingLoading = false) }
                    _effect.emit(HomeContract.Effect.ShowError(res.message))
                }

                is ContentState.Success<List<MovieUiModel>> -> {
                    _state.update {
                        it.copy(upcomingMovies = res.data, isUpcomingLoading = false
                        )
                    }
                }
            }
        }
    }

    private fun fetchTopRatedMovies() {
        viewModelScope.launch {
            _state.update { it.copy(isTopRatedLoading = true) }

            when (val res = fetchTopRatedUseCase()) {
                is ContentState.Error<*> -> {
                    _state.update { it.copy(isTopRatedLoading = false) }
                    _effect.emit(HomeContract.Effect.ShowError(res.message))
                }

                is ContentState.Success<List<MovieUiModel>> -> {
                    _state.update {
                        it.copy(topRatedMovies = res.data, isTopRatedLoading = false
                        )
                    }
                }
            }
        }
    }
}