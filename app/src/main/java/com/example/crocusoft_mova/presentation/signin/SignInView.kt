package com.example.crocusoft_mova.presentation.signin

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.crocusoft_mova.ui.navigation.AppRoutes


@Composable
fun SignInView(innerPadding: PaddingValues, navController: NavController,viewModel: SignInViewModel = hiltViewModel()) {


    val state by viewModel.state.collectAsStateWithLifecycle()

    SignInContent(
        postIntent = viewModel::onIntent,
        state = state,
        paddingValues = innerPadding,
        onNavigate = {navController.navigate(AppRoutes.SignUp.route)},
        onNavigateBack = {navController.popBackStack()}
    )
}