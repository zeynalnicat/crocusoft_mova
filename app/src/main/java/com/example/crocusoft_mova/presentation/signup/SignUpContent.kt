package com.example.crocusoft_mova.presentation.signup

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.crocusoft_mova.common.components.AppButton
import com.example.crocusoft_mova.common.components.AppCheckbox
import com.example.crocusoft_mova.common.components.AppIconButton
import com.example.crocusoft_mova.common.components.AppTextField
import com.example.crocusoft_mova.common.components.AppTopBar
import com.example.crocusoft_mova.common.components.SignContent
import com.example.crocusoft_mova.core.BaseTextStyle
import com.example.crocusoft_mova.core.BaseTheme
import com.example.crocusoft_mova.core.Colors
import com.example.crocusoft_mova.core.Drawables
import com.example.crocusoft_mova.core.Strings
import com.example.crocusoft_mova.presentation.signup.components.SignUpWithList


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SignUpContent(
    paddingValues: PaddingValues,
    state: SignUpContract.State,
    onNavigate: ()-> Unit,
    postIntent: (SignUpContract.Intent) -> Unit,
    onNavigateBack: () -> Unit,
) {

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
        onNavigateBack = onNavigateBack

    )
}


