package com.example.crocusoft_mova.presentation.auth.signin

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.example.crocusoft_mova.presentation.auth.components.SignContent
import kotlinx.coroutines.flow.SharedFlow


@Composable
fun SignInContent(
    postIntent: (SignInContract.Intent)->Unit,
    state: SignInContract.State,
    effect: SharedFlow<SignInContract.UiEffect>,
    onNavigate: ()->Unit,
    onNavigateBack: ()->Unit,
    onNavigateToChooseInterest: ()->Unit,
){

    LaunchedEffect(effect) {
        effect.collect {
            when(it){
                SignInContract.UiEffect.NavigateToChoose -> onNavigateToChooseInterest()
            }
        }
    }

    SignContent(
        email = state.email,
        password = state.password,
        onEmailChange = {postIntent(SignInContract.Intent.SetEmail(it))},
        onPasswordChange = {postIntent(SignInContract.Intent.SetPassword(it))},
        onSetChecked = {postIntent(SignInContract.Intent.SetChecked(it))},
        onSubmit = {postIntent(SignInContract.Intent.Submit)},
        isLoading = state.isLoading,
        checked = state.checked,
        onNavigate = onNavigate,
        isSignUp = false,
        onNavigateBack = onNavigateBack,
    )
}