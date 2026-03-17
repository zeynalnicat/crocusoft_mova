package com.example.crocusoft_mova.presentation.dashboard.profile

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.crocusoft_mova.common.components.AppTopBar
import com.example.crocusoft_mova.common.components.NotAnyComponent
import com.example.crocusoft_mova.common.components.ProfileAvatar
import com.example.crocusoft_mova.common.components.VerticalSpacer
import com.example.crocusoft_mova.core.BaseTheme
import com.example.crocusoft_mova.core.Colors
import com.example.crocusoft_mova.core.Drawables
import com.example.crocusoft_mova.core.Strings
import com.example.crocusoft_mova.presentation.dashboard.profile.components.SettingsItem
import com.example.crocusoft_mova.presentation.dashboard.profile.components.SettingsItemModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProfileContent(
    state: ProfileContract.State,
    postIntent: (ProfileContract.Intent) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
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


            item{
                VerticalSpacer(BaseTheme.dimens.dp6)
            }

            items(
                count = SettingsItemModel.items.size
            ) {
                SettingsItem(
                    item = SettingsItemModel.items[it]
                )
            }

        }

    }

}