package com.example.crocusoft_mova.presentation.auth.create_new_pin

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController


@Composable
fun CreateNewPin(innerPaddingValues: PaddingValues, navController: NavController,viewModel: CreateNewPinViewModel= hiltViewModel()){

    val state by viewModel.state.collectAsStateWithLifecycle()

    CreateNewPinContent(
        innerPaddingValues = innerPaddingValues,
        state = state,
        postIntent = viewModel::onIntent
    )

}