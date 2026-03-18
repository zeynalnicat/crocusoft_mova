package com.example.crocusoft_mova.presentation.dashboard.movie_detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController


@Composable
fun MovieDetailView(
    navController: NavController,
    movieId:Int,
    viewModel: MovieDetailViewModel = hiltViewModel()
) {

    LaunchedEffect(Unit) {
        viewModel.onIntent(MovieDetailContract.Intent.FetchMovieDetail(movieId))
    }

    val state by viewModel.state.collectAsStateWithLifecycle()


    MovieDetailContent(
        onNavigateBack = navController::popBackStack,
        state = state,
        postIntent = viewModel::onIntent
    )
}