package com.example.crocusoft_mova.presentation.dashboard.explore

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crocusoft_mova.core.BaseViewModel
import com.example.crocusoft_mova.core.ContentState
import com.example.crocusoft_mova.domain.models.MovieUiModel
import com.example.crocusoft_mova.domain.usecases.FetchTrendingMoviesUseCase
import com.example.crocusoft_mova.domain.usecases.SearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


@HiltViewModel
class ExploreViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase,
    private val fetchTrendingMoviesUseCase: FetchTrendingMoviesUseCase
) : BaseViewModel<ExploreContract.Intent, ExploreContract.State>() {

    private val _state = MutableStateFlow(ExploreContract.State())
    override val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<ExploreContract.Effect>()
    val effect = _effect.asSharedFlow()

    private val cachedMovies = MutableStateFlow<List<MovieUiModel>>(emptyList())


    init {

        fetchTrendingUseCase()
    }

    override fun onIntent(intent: ExploreContract.Intent) {
        when (intent) {
            is ExploreContract.Intent.SetQuery -> {
                _state.update { it.copy(searchQuery = intent.query) }
                viewModelScope.launch {
                    if (intent.query.isNotEmpty()) {
                        delay(500L)
                        searchMovie(intent.query)
                    } else {
                        _state.update { it.copy(movies = cachedMovies.value) }
                    }
                }
            }

            ExploreContract.Intent.Search -> {
                searchMovie(state.value.searchQuery)
            }
        }
    }


    private fun fetchTrendingUseCase(){
        viewModelScope.launch {
            when(val res = fetchTrendingMoviesUseCase()){
                is ContentState.Error<*> -> {
                    _effect.emit(ExploreContract.Effect.ShowError(res.message))
                }
                is ContentState.Success<List<MovieUiModel>> -> {
                    cachedMovies.update { res.data }
                    _state.update {
                        it.copy(movies = res.data)
                    }
                }
            }
        }
    }

    private fun searchMovie(query: String) {
        if (query.isBlank()) return

        viewModelScope.launch {
            when (val res = searchUseCase(query)) {
                is ContentState.Error<*> -> {
                    Log.i("movies", res.message)
                    _effect.emit(ExploreContract.Effect.ShowError(res.message ?: "Unknown Error"))
                    _state.update { it.copy(movies = emptyList()) }
                }

                is ContentState.Success<List<MovieUiModel>> -> {
                    _state.update {
                        it.copy(movies = res.data)
                    }
                    Log.i("movies", res.data.toString())
                }
            }
        }
    }
}
