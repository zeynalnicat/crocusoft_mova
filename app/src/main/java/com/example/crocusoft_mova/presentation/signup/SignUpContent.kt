package com.example.crocusoft_mova.presentation.signup

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.crocusoft_mova.common.components.AppButton
import com.example.crocusoft_mova.common.components.AppCheckbox
import com.example.crocusoft_mova.common.components.AppTextField
import com.example.crocusoft_mova.common.components.AppTopBar
import com.example.crocusoft_mova.core.BaseTextStyle
import com.example.crocusoft_mova.core.BaseTheme
import com.example.crocusoft_mova.core.Colors
import com.example.crocusoft_mova.core.Drawables
import com.example.crocusoft_mova.core.Strings


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SignUpContent(
    paddingValues: PaddingValues,
    state: SignUpContract.State,
    postIntent: (SignUpContract.Intent) -> Unit
) {

    Scaffold(
        topBar = {
            AppTopBar(
                prefixAction = {}
            )
        },
        containerColor = colorResource(Colors.primary),
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize(),

        ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = BaseTheme.dimens.dp15)
                .padding(horizontal = BaseTheme.dimens.dp4)

        ) {

            Icon(
                painter = painterResource(Drawables.logo),
                contentDescription = null,
                tint = colorResource(
                    Colors.secondary
                )
            )

            Spacer(modifier = Modifier.height(BaseTheme.dimens.dp4))

            Text(
                text = stringResource(Strings.createAccount),
                style = BaseTextStyle.t36Bold
            )

            Spacer(modifier = Modifier.height(BaseTheme.dimens.dp7))

            AppTextField(
                prefixIcon = Drawables.inbox,
                value = state.email,
                onValueChange = { postIntent(SignUpContract.Intent.SetEmail(it)) },
                placeholder = stringResource(Strings.email)
            )

            Spacer(modifier = Modifier.height(BaseTheme.dimens.dp4))

            AppTextField(
                prefixIcon = Drawables.lock,
                value = state.password,
                onValueChange = { postIntent(SignUpContract.Intent.SetPassword(it)) },
                isPasswordField = true,
                placeholder = stringResource(Strings.password)
            )

            Spacer(modifier = Modifier.height(BaseTheme.dimens.dp4))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                AppCheckbox(
                    checked = state.checked
                ) { postIntent(SignUpContract.Intent.SetChecked(it)) }

                Spacer(modifier = Modifier.width(BaseTheme.dimens.dp3))

                Text(
                    text = stringResource(Strings.remember),
                    style = BaseTheme.textStyle.t14SemiBold

                )

            }


            AppButton(
                action = { postIntent(SignUpContract.Intent.Submit) },
                text = stringResource(Strings.sign_up)
            )


        }
    }
}


