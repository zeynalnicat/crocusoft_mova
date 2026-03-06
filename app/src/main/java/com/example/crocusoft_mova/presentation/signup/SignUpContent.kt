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
    postIntent: (SignUpContract.Intent) -> Unit
) {

    Scaffold(
        topBar = {
            AppTopBar(prefixAction = {})
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
                    text = stringResource(Strings.createAccount),
                    style = BaseTextStyle.t36Bold
                )
            }

            item { Spacer(Modifier.height(BaseTheme.dimens.dp8)) }

            item {
                AppTextField(
                    prefixIcon = Drawables.inbox,
                    value = state.email,
                    onValueChange = {
                        postIntent(SignUpContract.Intent.SetEmail(it))
                    },
                    placeholder = stringResource(Strings.email)
                )
            }

            item { Spacer(Modifier.height(BaseTheme.dimens.dp4)) }

            item {
                AppTextField(
                    prefixIcon = Drawables.lock,
                    value = state.password,
                    onValueChange = {
                        postIntent(SignUpContract.Intent.SetPassword(it))
                    },
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
                        checked = state.checked
                    ) {
                        postIntent(SignUpContract.Intent.SetChecked(it))
                    }

                    Spacer(Modifier.width(BaseTheme.dimens.dp3))

                    Text(
                        text = stringResource(Strings.remember),
                        style = BaseTheme.textStyle.t14SemiBold
                    )
                }
            }

            item{
                AnimatedVisibility(
                    visible = state.isLoading,
                    enter = slideInHorizontally(
                        initialOffsetX = {  -it*3 }
                    ) + fadeIn() ,
                    exit = slideOutHorizontally (
                        targetOffsetX = {it*2}
                    ) + fadeOut()
                ) {
                    CircularProgressIndicator()
                }
            }

            item { Spacer(Modifier.height(BaseTheme.dimens.dp6)) }

            item {
                AppButton(
                    action = { postIntent(SignUpContract.Intent.Submit) },
                    text = stringResource(Strings.sign_up),
                )
            }

            item { Spacer(Modifier.height(BaseTheme.dimens.dp6)) }

            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {

                    HorizontalDivider(
                        modifier = Modifier.weight(1f),
                        thickness = 1.dp,
                    )

                    Text(
                        text = " or continue with ",
                        style = BaseTheme.textStyle.t14SemiBold
                    )

                    HorizontalDivider(
                        modifier = Modifier.weight(1f),
                        color = colorResource(Colors.gray).copy(alpha = 0.4f)
                    )
                }
            }

            item { Spacer(Modifier.height(BaseTheme.dimens.dp6)) }

            item {
                SignUpWithList()
            }

            item { Spacer(Modifier.height(BaseTheme.dimens.dp6)) }

            item {
                Row {
                    Text(
                        text =  stringResource(Strings.already),
                        style = BaseTheme.textStyle.t14SemiBold
                    )

                    Text(
                        text = stringResource(Strings.sign_in),
                        color = colorResource(Colors.secondary),
                        style = BaseTheme.textStyle.t14SemiBold
                    )
                }
            }

            item { Spacer(Modifier.height(BaseTheme.dimens.dp10)) }
        }
    }
}


