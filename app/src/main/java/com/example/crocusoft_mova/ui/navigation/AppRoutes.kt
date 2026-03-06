package com.example.crocusoft_mova.ui.navigation

import kotlinx.serialization.Serializable

sealed interface AppRoutes {

    @Serializable
    data object SignUp: AppRoutes

    @Serializable
    data object SignIn: AppRoutes


    @Serializable
    data object SignChoice: AppRoutes


    @Serializable
    data object ChooseInterest: AppRoutes


    @Serializable
    data object FillProfile: AppRoutes
}
