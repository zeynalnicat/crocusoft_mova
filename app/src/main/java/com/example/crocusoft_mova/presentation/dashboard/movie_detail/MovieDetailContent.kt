package com.example.crocusoft_mova.presentation.dashboard.movie_detail

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import coil3.compose.AsyncImage
import com.example.crocusoft_mova.common.components.AppTopBar
import com.example.crocusoft_mova.core.BaseTheme
import com.example.crocusoft_mova.core.Colors
import com.example.crocusoft_mova.core.Drawables
import com.example.crocusoft_mova.core.constants.ApiConstants
import com.example.crocusoft_mova.presentation.dashboard.movie_detail.components.MovieDetailHeader


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MovieDetailContent(
    state: MovieDetailContract.State,
    postIntent: (MovieDetailContract.Intent) -> Unit,
    onNavigateBack: () -> Unit
) {

    Scaffold(
        containerColor = colorResource(Colors.primary),
        topBar = {
            AppTopBar(
                prefixAction = onNavigateBack,
                suffixIcon = {
                    Icon(
                        painter = painterResource(Drawables.icon_cast),
                        contentDescription = null,
                        tint = colorResource(Colors.white)
                    )
                }
            )
        },

        ) {

        LazyColumn(
            modifier = Modifier.padding(BaseTheme.dimens.dp6)
        ) {

            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(
                            BaseTheme.dimens.detail_header_height
                        )
                ) {
                    AsyncImage(
                        model = ApiConstants.getPosterUrl(state.movieDetail.image),
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }

            item {
                MovieDetailHeader(
                    state = state,
                    postIntent = postIntent
                )
            }

        }


    }


}