package com.example.crocusoft_mova.presentation.dashboard.home

import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController


@Composable
fun HomeView(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {

   HomeContent()
}