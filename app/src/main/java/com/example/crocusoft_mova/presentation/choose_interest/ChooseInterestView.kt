package com.example.crocusoft_mova.presentation.choose_interest

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.crocusoft_mova.ui.navigation.AppRoutes


@Composable
fun ChooseInterestView(
    paddingValues: PaddingValues,
    navController: NavController,
    viewModel: ChooseInterestViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    ChooseInterestContent(
        innerPaddingValues = paddingValues,
        onNavigateBack = { navController.navigate(AppRoutes.ChooseInterest.route) },
        state = state,
        postIntent = viewModel::onIntent
    )

}