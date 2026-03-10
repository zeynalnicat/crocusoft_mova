package com.example.crocusoft_mova.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.crocusoft_mova.presentation.auth.choose_interest.ChooseInterestView
import com.example.crocusoft_mova.presentation.auth.create_new_pin.CreateNewPin
import com.example.crocusoft_mova.presentation.auth.fill_profile.FillProfileView
import com.example.crocusoft_mova.presentation.auth.login_choice.LoginChoiceView
import com.example.crocusoft_mova.presentation.auth.signin.SignInView
import com.example.crocusoft_mova.presentation.auth.signup.SignUpView

@Composable
fun MainNavHost(
    innerPadding: PaddingValues,
    navController: NavHostController,
) {


    NavHost(
        startDestination = AppRoutes.SignChoice,
        navController = navController,

        ) {
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

        composable<AppRoutes.NewPin> {
            CreateNewPin(
                innerPaddingValues = innerPadding,
                navController = navController
            )
        }
    }


}