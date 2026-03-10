package com.example.crocusoft_mova.presentation.auth.choose_interest

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
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
import com.example.crocusoft_mova.presentation.auth.choose_interest.components.TagChipList
import kotlinx.coroutines.flow.SharedFlow


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ChooseInterestContent(
    innerPaddingValues: PaddingValues,
    onNavigateBack: () -> Unit,
    onNavigateFillProfile: () -> Unit,
    postIntent: (ChooseInterestContract.Intent) -> Unit,
    state: ChooseInterestContract.State,
    effect: SharedFlow<ChooseInterestContract.Effect>
) {

    LaunchedEffect(effect) {
        effect.collect {
            when (it) {
                ChooseInterestContract.Effect.NavigateFillProfile -> onNavigateFillProfile()
            }
        }
    }


    LaunchedEffect(Unit) {
        postIntent(ChooseInterestContract.Intent.FetchTags)
    }


    Scaffold(
        modifier = Modifier.padding(innerPaddingValues),
        containerColor = colorResource(Colors.primary),
        topBar = {
            AppTopBar(
                prefixAction = onNavigateBack,
                title = stringResource(Strings.choose_interest)
            )
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(BaseTheme.dimens.dp4)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(BaseTheme.dimens.dp4),
                modifier = Modifier
                    .padding(vertical = BaseTheme.dimens.dp10)
                    .fillMaxSize()
            ) {

                Text(
                    text = stringResource(Strings.choose_interest_description),
                    style = BaseTheme.textStyle.t16Bold
                )

                TagChipList(
                    tags = state.tags,
                    selectedTags = state.selectedTags,
                    postIntent = postIntent
                )
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(BaseTheme.dimens.dp2),
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter),
            ) {
                AppButton(
                    modifier = Modifier.weight(1f),
                    action = onNavigateFillProfile,
                    text = stringResource(Strings.skip),
                    color = colorResource(Colors.dark2)
                )

                AppButton(
                    modifier = Modifier.weight(1f),
                    action = { postIntent(ChooseInterestContract.Intent.Continue) },
                    text = stringResource(Strings.btn_continue),
                    color = colorResource(Colors.secondary)
                )
            }
        }


    }
}