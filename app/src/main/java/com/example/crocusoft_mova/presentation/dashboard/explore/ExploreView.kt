package com.example.crocusoft_mova.presentation.dashboard.explore

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.crocusoft_mova.ui.navigation.AppRoutes


@Composable
fun ExploreView(navController: NavController, viewModel: ExploreViewModel = hiltViewModel()) {

    val state by viewModel.state.collectAsStateWithLifecycle()


    ExploreContent(
        state = state,
        postIntent = viewModel::onIntent,
        onNavigateDetail = { navController.navigate(AppRoutes.DashboardRoute.MovieDetail(it)) },
        effect = viewModel.effect
    )
}