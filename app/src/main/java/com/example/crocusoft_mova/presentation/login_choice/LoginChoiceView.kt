package com.example.crocusoft_mova.presentation.login_choice

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.crocusoft_mova.ui.navigation.AppRoutes


@Composable
fun LoginChoiceView(paddingValues: PaddingValues, navController: NavController) {


    LoginChoiceContent(
        paddingValues = paddingValues,
        onNavigateSignIn = { navController.navigate(AppRoutes.SignIn.route) },
        onNavigateSignUp = { navController.navigate(AppRoutes.SignUp.route) }
    )
}