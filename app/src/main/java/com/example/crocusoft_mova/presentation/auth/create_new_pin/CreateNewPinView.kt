package com.example.crocusoft_mova.presentation.auth.create_new_pin

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.crocusoft_mova.ui.navigation.AppRoutes


@Composable
fun CreateNewPin(navController: NavController,viewModel: CreateNewPinViewModel= hiltViewModel()){

    val state by viewModel.state.collectAsStateWithLifecycle()

    CreateNewPinContent(
        state = state,
        postIntent = viewModel::onIntent,
        effect = viewModel.effect,
        onNavigateHome = {navController.navigate(AppRoutes.DashboardRoute.Home)},
        onNavigateBack = navController::popBackStack
    )

}