package com.example.crocusoft_mova.presentation.dashboard.home


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crocusoft_mova.core.ContentState
import com.example.crocusoft_mova.domain.models.MovieUiModel
import com.example.crocusoft_mova.domain.usecases.FetchDiscoverMovieUseCase
import com.example.crocusoft_mova.domain.usecases.FetchDiscoverTVUseCase
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
    private val fetchDiscoverMovieUseCase: FetchDiscoverMovieUseCase,
    private val fetchDiscoverTVUseCase: FetchDiscoverTVUseCase,
    private val fetchUpcomingMoviesUseCase: FetchUpcomingMoviesUseCase
) :
    ViewModel() {


    private val _state = MutableStateFlow(HomeContract.State())
    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<HomeContract.Effect>()

    val effect = _effect.asSharedFlow()


    init {
        fetchDiscoverMovies()
        fetchDiscoverTv()
        fetchUpcomingMovies()

    }


    fun onIntent(intent: HomeContract.Intent) {
        when (intent) {
            HomeContract.Intent.FetchDiscoverMovies -> {}
            HomeContract.Intent.FetchUpcomingMovies -> {}
        }
    }

    private fun fetchDiscoverMovies() {
        viewModelScope.launch {
            when (val res = fetchDiscoverMovieUseCase()) {
                is ContentState.Error<*> -> {
                    Log.i("discoveries", res.message)
                    _effect.emit(HomeContract.Effect.ShowError(res.message))
                }

                is ContentState.Success<List<MovieUiModel>> -> {

                    _state.update { it.copy(discoverMovies = res.data) }
                    Log.i("discoveries", _state.value.discoverMovies.toString())
                }
            }

        }
    }

    private fun fetchDiscoverTv() {
        viewModelScope.launch {
            when (val res = fetchDiscoverTVUseCase()) {
                is ContentState.Error<*> -> {
                    Log.i("discoveries", res.message)
                    _effect.emit(HomeContract.Effect.ShowError(res.message))
                }

                is ContentState.Success<List<MovieUiModel>> -> {

                    _state.update { it.copy(discoverMovies = res.data) }
                    Log.i("discoveries", _state.value.discoverMovies.toString())
                }
            }

        }
    }
    private fun fetchUpcomingMovies() {
        viewModelScope.launch {
            when (val res = fetchUpcomingMoviesUseCase()) {
                is ContentState.Error<*> -> {
                    _effect.emit(HomeContract.Effect.ShowError(res.message))
                }

                is ContentState.Success<List<MovieUiModel>> -> {
                    _state.update { it.copy(upcomingMovies = res.data) }
                }
            }
        }
    }

}