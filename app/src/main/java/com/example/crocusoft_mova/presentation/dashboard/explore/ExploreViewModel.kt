package com.example.crocusoft_mova.presentation.dashboard.explore

import android.util.Log
import androidx.compose.ui.util.fastCbrt
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crocusoft_mova.core.ContentState
import com.example.crocusoft_mova.domain.models.GenreUiModel
import com.example.crocusoft_mova.domain.models.MovieUiModel
import com.example.crocusoft_mova.domain.models.RegionUiModel
import com.example.crocusoft_mova.domain.usecases.FetchDiscoverMovieUseCase
import com.example.crocusoft_mova.domain.usecases.FetchDiscoverTVUseCase
import com.example.crocusoft_mova.domain.usecases.FetchDiscoversUseCase
import com.example.crocusoft_mova.domain.usecases.FetchMovieGenresUseCase
import com.example.crocusoft_mova.domain.usecases.FetchRegionsUseCase
import com.example.crocusoft_mova.domain.usecases.FetchTrendingMoviesUseCase
import com.example.crocusoft_mova.domain.usecases.FetchTvGenresUseCase
import com.example.crocusoft_mova.domain.usecases.SearchUseCase
import com.example.crocusoft_mova.presentation.dashboard.explore.util.MovieCategory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
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
    private val fetchTrendingMoviesUseCase: FetchTrendingMoviesUseCase,
    private val fetchRegionsUseCase: FetchRegionsUseCase,
    private val fetchMovieGenresUseCase: FetchMovieGenresUseCase,
    private val fetchTvGenresUseCase: FetchTvGenresUseCase,
    /*private val fetchDiscoverTVUseCase: FetchDiscoverTVUseCase,
    private val fetchDiscoverMovieUseCase: FetchDiscoverMovieUseCase*/
    private val fetchDiscoversUseCase: FetchDiscoversUseCase
) : ViewModel() {

    private var searchJob : Job?= null

    private val _state = MutableStateFlow(ExploreContract.State())
    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<ExploreContract.Effect>()
    val effect = _effect.asSharedFlow()

    private val cachedMovies = MutableStateFlow<List<MovieUiModel>>(emptyList())


    init {

        fetchTrendingMovies()
        fetchRegions()
        fetchMovieGenre()
        fetchTvGenre()
    }

    fun onIntent(intent: ExploreContract.Intent) {
        when (intent) {
            is ExploreContract.Intent.SetQuery -> {
                _state.update { it.copy(searchQuery = intent.query) }
                    searchJob?.cancel()

                    if (intent.query.isNotEmpty()) {
                        searchJob = viewModelScope.launch {
                            delay(500L)
                            _state.update { it.copy(isLoading = true) }
                            searchMovie(intent.query)
                            _state.update { it.copy(isLoading = false) }

                        }
                    } else {
                        _state.update { it.copy(
                            movies = cachedMovies.value,
                            isLoading = false
                        ) }


                    }

            }
            is ExploreContract.Intent.ToggleFilterSheet -> {
                _state.update { it.copy(isBottomSheetVisible = intent.visibility) }
            }
            is ExploreContract.Intent.SelectCategory ->{
                _state.update { it.copy(
                    selectedCategory = intent.category,
                    selectedGenre = "All Genres") }

            }
            is ExploreContract.Intent.SelectRegion ->{
                _state.update { it.copy(selectedRegion = intent.region) }

            }
            is ExploreContract.Intent.SelectGenre ->{
                 _state.update { it.copy(
                     selectedGenre = intent.genre) }
            }
            is ExploreContract.Intent.SelectPeriod ->{
                _state.update { it.copy(selectedPeriod = intent.period) }
            }
            is ExploreContract.Intent.SelectSort ->{
                _state.update { it.copy(selectedSort = intent.sort) }
            }
            is ExploreContract.Intent.ApplyFilters ->{
                _state.update {it.copy(isLoading = true, isBottomSheetVisible = false)}
             viewModelScope.launch {
                 val currentState = _state.value

                 val genreId = currentState.getGenreId()
                 val regionCode = currentState.getRegionCode()
                 val apiSort = currentState.getApiSortOrder()
                 val releaseYear = currentState.selectedPeriod.takeIf { it != "All Periods" }

                 val apiCategory = if(currentState.selectedCategory== MovieCategory.MOVIE.title){
                     "movie"
                 } else{
                     "tv"
                 }
                 val result =
                     fetchDiscoversUseCase(apiCategory,regionCode,genreId,releaseYear,apiSort)
                   handleResult (result)

             }
            }
            is ExploreContract.Intent.ResetFilters ->{
              _state.update { it.copy(
                  selectedCategory = ExploreContract.State.DEFAULT_CATEGORY,
                  selectedRegion = ExploreContract.State.DEFAULT_REGION,
                  selectedGenre = ExploreContract.State.DEFAULT_GENRE,
                  selectedSort = ExploreContract.State.DEFAULT_SORT,
                  isLoading = false
              ) }
            }
        }

    }

    private suspend fun handleResult(result: ContentState<List<MovieUiModel>>) {
        when (result) {
            is ContentState.Error-> {
                _effect.emit(ExploreContract.Effect.ShowError(result.message))
                _state.update { it.copy(isLoading = false) }
            }
            is ContentState.Success -> {
                _state.update { it.copy(
                    movies = result.data,
                    isLoading = false
                ) }
            }
        }
    }

    private fun fetchRegions(){
        viewModelScope.launch {
            when(val res = fetchRegionsUseCase()){
                is ContentState.Error -> {
                    _effect.emit(ExploreContract.Effect.ShowError(res.message))
                }
                is ContentState.Success<List<RegionUiModel>> -> {
                    _state.update {
                        it.copy(regions = res.data)
                    }
                }
            }
        }
    }
    private fun fetchMovieGenre(){
        viewModelScope.launch {
            when(val res = fetchMovieGenresUseCase()){
                is ContentState.Error -> {
                    _effect.emit(ExploreContract.Effect.ShowError(res.message))
                }
                is ContentState.Success<List<GenreUiModel>> -> {
                    _state.update {
                        it.copy(movieGenres = res.data)
                    }
                }
            }
        }
    }

    private fun fetchTvGenre(){
        viewModelScope.launch {
            when(val res = fetchTvGenresUseCase()){
                is ContentState.Error<*> -> {
                    Log.i("TvGenreError",res.message)
                    _effect.emit(ExploreContract.Effect.ShowError(res.message))
                }
                is ContentState.Success<List<GenreUiModel>> -> {
                    Log.i("MovieGenre :", res.data.toString())
                    _state.update {
                        it.copy(tvGenres = res.data)
                    }
                }
            }
        }
    }


    private fun fetchTrendingMovies(){
        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            when(val res = fetchTrendingMoviesUseCase()){
                is ContentState.Error<*> -> {
                    _effect.emit(ExploreContract.Effect.ShowError(res.message))
                    _state.update { it.copy(isLoading = false) }
                }
                is ContentState.Success<List<MovieUiModel>> -> {
                    cachedMovies.update { res.data }
                    _state.update {
                        it.copy(movies = res.data,
                            isLoading = false)
                    }
                }
            }
        }
    }

    private suspend fun searchMovie(query: String) {
        if (query.isBlank()) return
           when (val res = searchUseCase(query)) {
                is ContentState.Error -> {
                    _effect.emit(ExploreContract.Effect.ShowError(res.message))
                    _state.update { it.copy(movies = emptyList()) }
                }

                is ContentState.Success<List<MovieUiModel>> -> {
                    _state.update {
                        it.copy(movies = res.data)
                    }
                }
            }
        }

}
