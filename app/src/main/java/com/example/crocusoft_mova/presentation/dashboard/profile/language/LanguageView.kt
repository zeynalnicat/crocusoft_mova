package com.example.crocusoft_mova.presentation.dashboard.profile.language

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController

@Composable
fun LanguageView(
    viewModel: LanguageViewModel = hiltViewModel(),
    navController: NavController
){
    val state by viewModel.state.collectAsStateWithLifecycle()
    LanguageScreen(
        state = state,
        postIntent = viewModel::onIntent,
        onBackClick = { navController.popBackStack() }
    )

}