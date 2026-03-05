package com.example.crocusoft_mova

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.crocusoft_mova.core.Colors
import com.example.crocusoft_mova.presentation.signup.SignUp
import com.example.crocusoft_mova.ui.theme.Crocusoft_movaTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Crocusoft_movaTheme {
                Scaffold(
                    containerColor = colorResource(Colors.primary),
                    modifier = Modifier
                        .fillMaxSize()


                ) { innerPadding ->
                    SignUp(innerPadding)
                }
            }
        }
    }
}
