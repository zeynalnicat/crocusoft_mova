package com.example.crocusoft_mova.presentation.auth.create_new_pin

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import com.example.crocusoft_mova.common.components.AppButton
import com.example.crocusoft_mova.common.components.AppTopBar
import com.example.crocusoft_mova.core.BaseTheme
import com.example.crocusoft_mova.core.Colors
import com.example.crocusoft_mova.core.Strings
import com.example.crocusoft_mova.presentation.auth.create_new_pin.components.OtpInput
import kotlinx.coroutines.flow.SharedFlow


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CreateNewPinContent(
    postIntent: (CreateNewPinContract.Intent)->Unit,
    state: CreateNewPinContract.State,
    effect : SharedFlow<CreateNewPinContract.Effect>,
    onNavigateHome: ()->Unit,
){

    val scrollState = rememberScrollState()

    LaunchedEffect(effect) {
         effect.collect {
             when(it){
                 CreateNewPinContract.Effect.NavigateHome -> onNavigateHome()
             }
         }
    }

    Scaffold(
        containerColor = colorResource(Colors.primary),
        topBar = {
            AppTopBar(
                prefixAction = {},
                title = stringResource(Strings.create_new_pin)
            )
        }
    ) {
        Box(
            modifier = Modifier.fillMaxSize().padding(BaseTheme.dimens.dp4)
        ){
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize().verticalScroll(scrollState)
                    .padding(BaseTheme.dimens.dp6)
            ) {
                Text(
                    text = stringResource(Strings.new_pin_description),
                    style = BaseTheme.textStyle.t18SemiBold
                )

                Spacer(modifier = Modifier.height(BaseTheme.dimens.dp6))


                OtpInput(
                    otpValues = state.pin,
                    onValueChange = { index, pin-> postIntent(CreateNewPinContract.Intent.SetPin(index,pin))}

                )
            }

            AppButton(
                modifier = Modifier.fillMaxWidth().align(Alignment.BottomCenter),
                action = {
                    postIntent(CreateNewPinContract.Intent.Submit)
                },
                text = stringResource(Strings.btn_continue),
                color = colorResource(Colors.secondary)
            )
        }
    }

}
