package com.example.crocusoft_mova.ui.navigation

import kotlinx.serialization.Serializable

sealed interface AppRoutes {


    sealed interface AuthRoute {
        @Serializable
        data object NewPin: AuthRoute

        @Serializable
        data object SignUp: AuthRoute

        @Serializable
        data object SignIn: AuthRoute


        @Serializable
        data object SignChoice: AuthRoute


        @Serializable
        data object ChooseInterest: AuthRoute


        @Serializable
        data object FillProfile: AuthRoute
    }

    sealed interface DashboardRoute{
        @Serializable
        data object Home:DashboardRoute
    }



}