package com.example.crocusoft_mova.presentation.dashboard.profile

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.crocusoft_mova.ui.navigation.AppRoutes


@Composable
fun ProfileView(navController: NavController, viewModel: ProfileViewModel = hiltViewModel()) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    ProfileContent(
        state = state,
        effect = viewModel.effect,
        postIntent = viewModel::onIntent,
        onNavigateSignChoice = {
            navController.navigate(AppRoutes.AuthRoute.Splash) {
                popUpTo(
                    0
                ) {
                    inclusive = true

                }
                launchSingleTop = true
            }
        }
    )
}