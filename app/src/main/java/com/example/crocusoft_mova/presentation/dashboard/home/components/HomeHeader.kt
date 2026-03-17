package com.example.crocusoft_mova.presentation.dashboard.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage
import com.example.crocusoft_mova.common.components.AppIconButton
import com.example.crocusoft_mova.core.BaseTextStyle
import com.example.crocusoft_mova.core.BaseTheme
import com.example.crocusoft_mova.core.Colors
import com.example.crocusoft_mova.core.Drawables
import com.example.crocusoft_mova.core.Strings
import com.example.crocusoft_mova.domain.models.MovieUiModel
import com.example.crocusoft_mova.presentation.dashboard.home.HomeContract

@Composable
fun HomeHeader(
    movieUiModel: MovieUiModel,
    postIntent: (HomeContract.Intent) -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(BaseTheme.dimens.home_header_cover_height)
    ) {
        AsyncImage(
            modifier = Modifier.fillMaxWidth(),
            model = movieUiModel.image,
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(BaseTheme.dimens.dp2),
            modifier = Modifier
                .fillMaxWidth()
                .padding(BaseTheme.dimens.dp6)
                .align(Alignment.BottomStart)
        ) {

            Text(
                text = movieUiModel.title,
                style = BaseTextStyle.t24Bold,

                )

            Text(
                text = movieUiModel.description,
                style = BaseTextStyle.t12,
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(BaseTheme.dimens.dp2)

            ) {
                AppIconButton(
                    color = Colors.secondary,
                    borderColor = Colors.secondary,
                    icon = Drawables.icon_play,
                    textRes = Strings.play,
                    radius = BaseTheme.dimens.radius,
                    iconSize = BaseTheme.dimens.dp3,
                    horizontalPadding = BaseTheme.dimens.dp4,
                    verticalPadding = BaseTheme.dimens.dp06
                ) { }

                AppIconButton(
                    color = Colors.transparent,
                    borderColor = Colors.white,
                    icon = Drawables.icon_plus,
                    iconSize = BaseTheme.dimens.dp3,
                    textRes = Strings.my_list,
                    radius = BaseTheme.dimens.radius,
                    horizontalPadding = BaseTheme.dimens.dp4,
                    verticalPadding = BaseTheme.dimens.dp06
                ) { }
            }


        }
    }
}