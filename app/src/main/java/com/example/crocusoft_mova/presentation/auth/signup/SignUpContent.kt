package com.example.crocusoft_mova.presentation.auth.signup

import android.annotation.SuppressLint

import androidx.compose.foundation.layout.PaddingValues

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

import com.example.crocusoft_mova.common.components.SignContent

import kotlinx.coroutines.flow.SharedFlow


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SignUpContent(
    paddingValues: PaddingValues,
    state: SignUpContract.State,
    onNavigate: ()-> Unit,
    postIntent: (SignUpContract.Intent) -> Unit,
    onNavigateBack: () -> Unit,
    onNavigateToChooseInterest: ()->Unit,
    effect: SharedFlow<SignUpContract.UiEffect>
) {

    LaunchedEffect(effect) {
        effect.collect {
            when(it){
                SignUpContract.UiEffect.NavigateToChoose -> onNavigateToChooseInterest()
            }
        }
    }

    SignContent(
        paddingValues = paddingValues,
        email = state.email,
        password = state.password,
        onEmailChange = {postIntent(SignUpContract.Intent.SetEmail(it))},
        onPasswordChange = {postIntent(SignUpContract.Intent.SetPassword(it))},
        onSetChecked = {postIntent(SignUpContract.Intent.SetChecked(it))},
        onSubmit = {postIntent(SignUpContract.Intent.Submit)},
        isLoading = state.isLoading,
        checked = state.checked,
        isSignUp = true,
        onNavigate = onNavigate,
        onNavigateBack = onNavigateBack,

    )
}


