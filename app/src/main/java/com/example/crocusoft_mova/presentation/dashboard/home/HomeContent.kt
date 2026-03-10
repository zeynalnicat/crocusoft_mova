package com.example.crocusoft_mova.presentation.dashboard.home

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import com.example.crocusoft_mova.core.Colors


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeContent() {

    Scaffold(
      containerColor = colorResource(Colors.primary)
    ) {

        Text("Home")

    }
}