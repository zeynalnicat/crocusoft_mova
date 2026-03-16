package com.example.crocusoft_mova.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.crocusoft_mova.presentation.auth.choose_interest.ChooseInterestView
import com.example.crocusoft_mova.presentation.auth.create_new_pin.PinView
import com.example.crocusoft_mova.presentation.auth.fill_profile.FillProfileView
import com.example.crocusoft_mova.presentation.auth.login_choice.LoginChoiceView
import com.example.crocusoft_mova.presentation.auth.signin.SignInView
import com.example.crocusoft_mova.presentation.auth.signup.SignUpView
import com.example.crocusoft_mova.presentation.dashboard.home.HomeView
import com.google.firebase.auth.FirebaseAuth

@Composable
fun AuthRoutes(
    innerPadding: PaddingValues,
    navController: NavHostController,
) {


    NavHost(
        startDestination = AppRoutes.AuthRoute.SignChoice,
        navController = navController,

        ) {
        composable<AppRoutes.AuthRoute.SignUp> {
            SignUpView(
                innerPadding = innerPadding,
                navController
            )
        }
        composable<AppRoutes.AuthRoute.SignIn> {
            SignInView(
                navController
            )
        }
        composable<AppRoutes.AuthRoute.SignChoice> {
            LoginChoiceView(
                navController
            )
        }

        composable<AppRoutes.AuthRoute.ChooseInterest> {
            ChooseInterestView(
                navController = navController
            )
        }

        composable<AppRoutes.AuthRoute.FillProfile> {
            FillProfileView(
                navController = navController
            )
        }

        composable<AppRoutes.AuthRoute.NewPin> {
            PinView(
                navController = navController
            )
        }

        composable<AppRoutes.DashboardRoute.Home> {
            HomeView(
                navController = navController
            )
        }
    }


}