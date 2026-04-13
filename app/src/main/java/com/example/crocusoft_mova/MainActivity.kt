package com.example.crocusoft_mova

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import com.example.crocusoft_mova.core.Colors
import com.example.crocusoft_mova.presentation.App
import com.example.crocusoft_mova.ui.theme.Crocusoft_movaTheme
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Crocusoft_movaTheme(darkTheme = false) {
                Scaffold(
                    containerColor = colorResource(Colors.primary),
                    modifier = Modifier
                        .fillMaxSize()


                ) { innerPadding ->
                    App(innerPaddingValues = innerPadding, firebaseAuth)
                }
            }
        }
    }
}
