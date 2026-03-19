package com.example.crocusoft_mova.domain.usecases

import com.example.crocusoft_mova.core.ContentState
import com.example.crocusoft_mova.domain.models.MovieDetailUiModel
import com.example.crocusoft_mova.domain.repository.MovieDetailRepository
import javax.inject.Inject

class AddToMyListUseCase @Inject constructor(
    private val repository: MovieDetailRepository
) {

    suspend operator fun invoke(movie: MovieDetailUiModel): ContentState<Unit> =
        repository.addMovieToWatchList(movie)

}