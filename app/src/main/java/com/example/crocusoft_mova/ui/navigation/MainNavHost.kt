package com.example.crocusoft_mova.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.crocusoft_mova.presentation.choose_interest.ChooseInterestView
import com.example.crocusoft_mova.presentation.fill_profile.FillProfileView
import com.example.crocusoft_mova.presentation.login_choice.LoginChoiceView
import com.example.crocusoft_mova.presentation.signin.SignInView
import com.example.crocusoft_mova.presentation.signup.SignUpView

@Composable
fun MainNavHost(
    innerPadding: PaddingValues,
    navController: NavHostController,
) {


    NavHost(
        startDestination = AppRoutes.SignChoice,
        navController = navController,

        ) {
        // TODO: replace with serializable data class
        composable<AppRoutes.SignUp> {
            SignUpView(
                innerPadding = innerPadding,
                navController
            )
        }
        composable<AppRoutes.SignIn> {
            SignInView(
                innerPadding = innerPadding,
                navController
            )
        }
        composable<AppRoutes.SignChoice> {
            LoginChoiceView(
                paddingValues = innerPadding,
                navController
            )
        }

        composable<AppRoutes.ChooseInterest> {
            ChooseInterestView(
                paddingValues = innerPadding,
                navController = navController
            )
        }

        composable<AppRoutes.FillProfile> {
            FillProfileView(
                paddingValues = innerPadding,
                navController = navController
            )
        }
    }


}