package com.example.crocusoft_mova.presentation.dashboard.profile

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import com.example.crocusoft_mova.common.components.AppTopBar
import com.example.crocusoft_mova.common.components.ProfileAvatar
import com.example.crocusoft_mova.common.components.VerticalSpacer
import com.example.crocusoft_mova.core.BaseTheme
import com.example.crocusoft_mova.core.Colors
import com.example.crocusoft_mova.core.Drawables
import com.example.crocusoft_mova.core.Strings
import com.example.crocusoft_mova.presentation.dashboard.profile.components.SettingsItem
import com.example.crocusoft_mova.presentation.dashboard.profile.components.SettingsItemModel
import kotlinx.coroutines.flow.SharedFlow


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProfileContent(
    state: ProfileContract.State,
    effect: SharedFlow<ProfileContract.Effect>,
    postIntent: (ProfileContract.Intent) -> Unit,
    onNavigateSignChoice: () -> Unit,
    onNavigateToLanguage : () -> Unit,
    onNavigateFillProfile : (Boolean) -> Unit
) {

    val snackBarHostState = remember { SnackbarHostState() }

    val settingItems = listOf(
        SettingsItemModel(
            icon = Drawables.profile,
            titleRes = Strings.edit_profile,
            action = {
                onNavigateFillProfile(true)
            }),
        SettingsItemModel(
            icon = Drawables.icon_notification,
            titleRes = Strings.notification,
            action = {}),
        SettingsItemModel(
            icon = Drawables.download,
            titleRes = Strings.download,
            action = {}),
        SettingsItemModel(
            icon = Drawables.icon_help,
            titleRes = Strings.help_center,
            action = {}),
        SettingsItemModel(
            icon = Drawables.language_icon,
            trailingText = "",
            titleRes = Strings.change_language,
            action = {
               onNavigateToLanguage()
            }),
        SettingsItemModel(
            icon = Drawables.mode,
            titleRes = Strings.mode,
            isSwitch = true,
            action = {}),
        SettingsItemModel(
            icon = Drawables.icon_help,
            titleRes = Strings.log_out,
            action = {
                postIntent(
                    ProfileContract.Intent.LogOut
                )
            }),

        )


    LaunchedEffect(effect) {
        effect.collect {
            when (it) {
                ProfileContract.Effect.NavigateSignChoice -> onNavigateSignChoice()
                is ProfileContract.Effect.ShowError -> snackBarHostState.showSnackbar(it.message)
            }
        }

    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding(),
        topBar = {
            AppTopBar(
                modifier = Modifier.padding(top = BaseTheme.dimens.dp2),
                prefixIcon = Drawables.logo,
                prefixColor = Colors.secondary,
                title = stringResource(Strings.profile),
                prefixAction = {},

                )
        },
        containerColor = colorResource(Colors.primary)
    ) {
        if(state.isLoading){
            Box(modifier = Modifier.fillMaxSize()
                .background(colorResource(Colors.primary).copy(alpha = 0.7f)),
                contentAlignment = Alignment.Center){

                CircularProgressIndicator()
            }
        }

        LazyColumn(
            modifier = Modifier.padding(BaseTheme.dimens.dp5)
        ) {
            item {

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(BaseTheme.dimens.dp3),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = BaseTheme.dimens.dp8),
                ) {
                    ProfileAvatar(
                        avatar = null,
                    )
                    Text(
                        text = state.profile.fullName,
                        style = BaseTheme.textStyle.t20Bold
                    )

                    Text(
                        text = state.profile.email,
                        style = BaseTheme.textStyle.t14SemiBold
                    )
                }
            }


            item {
                VerticalSpacer(BaseTheme.dimens.dp6)
            }

            items(
                count = settingItems.size
            ) {
                SettingsItem(
                    item = settingItems[it]
                )
            }

            item {
                VerticalSpacer(BaseTheme.dimens.dp6)

            }


        }

    }

}