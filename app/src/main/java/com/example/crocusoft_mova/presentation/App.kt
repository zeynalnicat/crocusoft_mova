package com.example.crocusoft_mova.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.crocusoft_mova.core.BaseTheme
import com.example.crocusoft_mova.core.Colors
import com.example.crocusoft_mova.ui.navigation.AppRoutes
import com.example.crocusoft_mova.ui.navigation.AuthRoutes


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun App(innerPaddingValues: PaddingValues) {

    val navController = rememberNavController()

    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route

    val dashboardRoutes = listOf(
        AppRoutes.DashboardRoute.Home
    )

    Scaffold(
        bottomBar = {
           val hiddenRoutes = listOf(
               AppRoutes.AuthRoute.NewPin.toString(),
               AppRoutes.AuthRoute.SignUp.toString(),
               AppRoutes.AuthRoute.SignIn.toString(),
               AppRoutes.AuthRoute.ChooseInterest.toString(),
               AppRoutes.AuthRoute.FillProfile.toString(),
           )


        },
        containerColor = colorResource(Colors.primary),
        modifier = Modifier.padding(innerPaddingValues)
    ) {

        AuthRoutes(innerPadding = innerPaddingValues, navController = navController)
    }

}