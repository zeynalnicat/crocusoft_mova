package com.example.crocusoft_mova.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.crocusoft_mova.presentation.dashboard.home.HomeView

@Composable
fun DashboardRoutes(
    navController: NavHostController,
) {


    NavHost(
        startDestination = AppRoutes.DashboardRoute.Home,
        navController = navController,

        ) {
        composable<AppRoutes.DashboardRoute.Home> {
            HomeView(

                navController = navController
            )
        }

    }


}