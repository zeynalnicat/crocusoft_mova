package com.example.crocusoft_mova.presentation.dashboard.profile

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController


@Composable
fun ProfileView(navController: NavController, viewModel: ProfileViewModel = hiltViewModel()) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    ProfileContent(
        state = state,
        postIntent = viewModel::onIntent
    )
}