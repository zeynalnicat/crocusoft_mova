package com.example.crocusoft_mova.presentation.dashboard.movie_detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import com.example.crocusoft_mova.common.components.AppIconButton
import com.example.crocusoft_mova.common.components.HorizontalSpacer
import com.example.crocusoft_mova.core.BaseTheme
import com.example.crocusoft_mova.core.Colors
import com.example.crocusoft_mova.core.Drawables
import com.example.crocusoft_mova.core.Strings
import com.example.crocusoft_mova.core.constants.ApiConstants
import com.example.crocusoft_mova.presentation.dashboard.movie_detail.MovieDetailContract


@Composable

fun MovieDetailHeader(
    state: MovieDetailContract.State,
    postIntent: (MovieDetailContract.Intent) -> Unit
) {

    Column(
        verticalArrangement = Arrangement.spacedBy(BaseTheme.dimens.dp5),
        modifier = Modifier.fillMaxWidth()
    ) {

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = state.movieDetail.title,
                style = BaseTheme.textStyle.t16Bold

            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(BaseTheme.dimens.dp2)
            ) {
                IconButton(
                    onClick = { /*TODO*/ }
                ) {
                    Icon(
                        painter = painterResource(Drawables.my_list),
                        contentDescription = null,
                        tint = colorResource(Colors.white)
                    )
                }
                IconButton(
                    onClick = { /*TODO*/ }
                ) {
                    Icon(
                        painter = painterResource(Drawables.icon_send),
                        contentDescription = null,
                        tint = colorResource(Colors.white)
                    )
                }
            }


        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Icon(
                painter = painterResource(Drawables.icon_star),
                contentDescription = null,
                tint = colorResource(Colors.secondary)
            )

            HorizontalSpacer(BaseTheme.dimens.dp2)

            Text(
                text = state.movieDetail.vote_average.toString(),
                style = BaseTheme.textStyle.t12.copy(color = colorResource(Colors.secondary))
            )

            HorizontalSpacer(BaseTheme.dimens.dp2)

            Icon(
                modifier = Modifier.size(
                    width = BaseTheme.dimens.dp2,
                    height = BaseTheme.dimens.dp1
                ),
                painter = painterResource(Drawables.icon_right),
                contentDescription = null,
                tint = colorResource(Colors.secondary)
            )

            HorizontalSpacer(BaseTheme.dimens.dp2)

            Text(
                text = state.movieDetail.release_date,
                style = BaseTheme.textStyle.t14SemiBold
            )

            HorizontalSpacer(BaseTheme.dimens.dp2)

            Box(
                modifier = Modifier
                    .clip(
                        RoundedCornerShape(BaseTheme.dimens.dp06)
                    )
                    .border(
                        width = BaseTheme.dimens.dp01,
                        color = colorResource(Colors.secondary),
                        shape = RoundedCornerShape(BaseTheme.dimens.dp06)

                    )
                    .background(
                        color = Color.Transparent,
                        shape = RoundedCornerShape(BaseTheme.dimens.dp06)
                    )
            ) {
                Text(
                    text = state.movieDetail.language,
                    style = BaseTheme.textStyle.t12.copy(color = colorResource(Colors.secondary)),

                    )
            }

        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(BaseTheme.dimens.dp2)
        ) {
            AppIconButton(
                modifier = Modifier.weight(1f),
                onClick = {},
                icon = Drawables.icon_play,
                iconSize = BaseTheme.dimens.dp4,
                textRes = Strings.play,
                color = Colors.secondary,
                borderColor = Colors.secondary,
            )

            AppIconButton(
                modifier = Modifier.weight(1f),
                onClick = {},
                icon = Drawables.download,
                textRes = Strings.download,
                iconSize = BaseTheme.dimens.dp4,
                borderColor = Colors.secondary,
                color = Colors.transparent,
                textColor = Colors.secondary
            )


        }

        Text(
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            text = "Genre: ${state.movieDetail.genres.joinToString { "," }}",
            style = BaseTheme.textStyle.t12
        )

        Text(
            maxLines = 3,
            overflow = TextOverflow.Ellipsis,
            text = state.movieDetail.description,
            style = BaseTheme.textStyle.t12
        )

        if (state.movieDetail.production_companies.isNotEmpty()) {
            ProductionCompanyItem(
                model = ApiConstants.getPosterUrl(state.movieDetail.production_companies[0].logo_path),
                title = state.movieDetail.production_companies[0].name


            )
        }


    }


}