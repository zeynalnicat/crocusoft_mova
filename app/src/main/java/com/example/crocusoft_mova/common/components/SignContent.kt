package com.example.crocusoft_mova.common.components

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
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
import com.example.crocusoft_mova.core.BaseTextStyle
import com.example.crocusoft_mova.core.BaseTheme
import com.example.crocusoft_mova.core.Colors
import com.example.crocusoft_mova.core.Drawables
import com.example.crocusoft_mova.core.Strings
import com.example.crocusoft_mova.presentation.signup.SignUpContract
import com.example.crocusoft_mova.presentation.signup.components.SignUpWithList


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SignContent(
    paddingValues: PaddingValues,
    email: String,
    password: String,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onSetChecked: (Boolean) -> Unit,
    onNavigate: () -> Unit,
    onSubmit: () -> Unit,
    isLoading: Boolean,
    checked: Boolean,
    isSignUp: Boolean = true,
    onNavigateToChooseInterest: ()->Unit,
    onNavigateBack: () -> Unit = {},



    ) {

    Scaffold(
        topBar = {
            AppTopBar(prefixAction = onNavigateBack)
        },
        containerColor = colorResource(Colors.primary),
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
    ) {

        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = BaseTheme.dimens.dp5),
        ) {

            item { Spacer(Modifier.height(BaseTheme.dimens.dp12)) }

            item {
                Icon(
                    painter = painterResource(Drawables.logo),
                    contentDescription = null,
                    tint = colorResource(Colors.secondary),
                )
            }

            item { Spacer(Modifier.height(BaseTheme.dimens.dp6)) }

            item {
                Text(
                    text = stringResource(if (isSignUp) Strings.createAccount else Strings.login),
                    style = BaseTextStyle.t36Bold
                )
            }

            item { Spacer(Modifier.height(BaseTheme.dimens.dp8)) }

            item {
                AppTextField(
                    prefixIcon = Drawables.inbox,
                    value = email,
                    onValueChange = onEmailChange,
                    placeholder = stringResource(Strings.email)
                )
            }

            item { Spacer(Modifier.height(BaseTheme.dimens.dp4)) }

            item {
                AppTextField(
                    prefixIcon = Drawables.lock,
                    value = password,
                    onValueChange = onPasswordChange,
                    isPasswordField = true,
                    placeholder = stringResource(Strings.password)
                )
            }

            item { Spacer(Modifier.height(BaseTheme.dimens.dp5)) }

            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    AppCheckbox(
                        checked = checked
                    ) {
                        onSetChecked(it)
                    }

                    Spacer(Modifier.width(BaseTheme.dimens.dp3))

                    Text(
                        text = stringResource(Strings.remember),
                        style = BaseTheme.textStyle.t14SemiBold
                    )
                }
            }

            item {
                AnimatedVisibility(
                    visible = isLoading,
                    enter = slideInHorizontally(
                        initialOffsetX = { -it * 3 }
                    ) + fadeIn(),
                    exit = slideOutHorizontally(
                        targetOffsetX = { it * 2 }
                    ) + fadeOut()
                ) {
                    CircularProgressIndicator()
                }
            }

            item { Spacer(Modifier.height(BaseTheme.dimens.dp6)) }

            item {
                AppButton(
                    action = onSubmit,
                    text = stringResource(if (isSignUp) Strings.sign_up else Strings.sign_in),
                )
            }

            item { Spacer(Modifier.height(BaseTheme.dimens.dp6)) }

            if (!isSignUp) {
                item {
                    Text(
                        text = stringResource(Strings.forgot),
                        style = BaseTheme.textStyle.t16SemiBoldRed
                    )
                }

                item { Spacer(Modifier.height(BaseTheme.dimens.dp6)) }

            }


            item {
                DividerWithText(" or continue with ")
            }

            item { Spacer(Modifier.height(BaseTheme.dimens.dp6)) }

            item {
                SignUpWithList()
            }

            item { Spacer(Modifier.height(BaseTheme.dimens.dp6)) }

            item {
                 ClickableText(
                     preText = stringResource(if (isSignUp) Strings.already else Strings.donthave),
                     sufText = stringResource(if (isSignUp) Strings.sign_in else Strings.sign_up)
                 ) {
                     onNavigate()
                 }
            }

            item { Spacer(Modifier.height(BaseTheme.dimens.dp10)) }
        }
    }
}