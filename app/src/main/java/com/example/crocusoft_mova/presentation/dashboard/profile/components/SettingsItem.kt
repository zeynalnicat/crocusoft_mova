package com.example.crocusoft_mova.presentation.dashboard.profile.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.crocusoft_mova.core.BaseTheme
import com.example.crocusoft_mova.core.Colors
import com.example.crocusoft_mova.core.Drawables
import com.example.crocusoft_mova.core.Strings

@Composable
fun SettingsItem(item: SettingsItemModel) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = BaseTheme.dimens.dp5)
            .clickable(
                onClick = item.action
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(BaseTheme.dimens.dp3)
        ) {
            Icon(
                painter = painterResource(item.icon),
                contentDescription = null,
                tint = colorResource(Colors.white)
            )

            Text(
                text = stringResource(item.titleRes),
                style = BaseTheme.textStyle.t14SemiBold
            )
        }

        Icon(
            painter = painterResource(Drawables.icon_right),
            contentDescription = null,
            tint = colorResource(Colors.white)
        )
    }

}


data class SettingsItemModel(
    val icon: Int,
    val titleRes: Int,
    val action: () -> Unit

) {

    companion object {
        val items = listOf<SettingsItemModel>(
            SettingsItemModel(
                icon = Drawables.profile,
                titleRes = Strings.edit_profile,
                action = {}),
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

            )
    }
}

