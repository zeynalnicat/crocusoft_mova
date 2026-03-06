package com.example.crocusoft_mova.ui.navigation

sealed class AppRoutes(val route:String) {

    data object SignUp: AppRoutes("sign-up")

    data object SignIn: AppRoutes("sign-in")

    data object SignChoice: AppRoutes("sign-choice")

    data object ChooseInterest: AppRoutes("choose-interest")
}