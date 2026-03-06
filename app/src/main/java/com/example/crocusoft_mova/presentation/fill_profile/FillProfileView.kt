package com.example.crocusoft_mova.presentation.fill_profile

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController


@Composable
fun FillProfileView(
    paddingValues: PaddingValues,
    viewModel: FillProfileViewModel = hiltViewModel(),
    navController: NavController
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    FillProfileContent(
        paddingValues = paddingValues,
        state = state,
        postIntent = viewModel::onIntent,
        onNavigateBack = {navController.popBackStack()}
    )
}