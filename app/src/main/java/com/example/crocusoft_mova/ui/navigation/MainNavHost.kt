package com.example.crocusoft_mova.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.crocusoft_mova.presentation.login_choice.LoginChoiceView
import com.example.crocusoft_mova.presentation.signin.SignInView
import com.example.crocusoft_mova.presentation.signup.SignUpView

@Composable
fun MainNavHost(
    innerPadding: PaddingValues,
    navController: NavHostController,
) {


    NavHost(
        startDestination = AppRoutes.SignChoice.route,
        navController = navController,

        ) {
        composable(AppRoutes.SignUp.route) {
            SignUpView(
                innerPadding = innerPadding,
                navController
            )
        }
        composable(AppRoutes.SignIn.route) {
            SignInView(
                innerPadding = innerPadding,
                navController
            )
        }
        composable(AppRoutes.SignChoice.route) {
            LoginChoiceView(
                paddingValues = innerPadding,
                navController
            )
        }
    }


}