package com.example.crocusoft_mova.presentation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.crocusoft_mova.ui.navigation.MainNavHost


@Composable
fun App(innerPaddingValues: PaddingValues) {

    val navController = rememberNavController()
    MainNavHost(innerPadding = innerPaddingValues, navController = navController)
}