package com.example.crocusoft_mova.presentation.dashboard.home

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import com.example.crocusoft_mova.core.Strings
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.crocusoft_mova.common.components.AppTopBar
import com.example.crocusoft_mova.common.components.MovieCoverItem
import com.example.crocusoft_mova.core.BaseTheme
import com.example.crocusoft_mova.core.Colors
import com.example.crocusoft_mova.core.Drawables
import com.example.crocusoft_mova.domain.models.MovieUiModel
import com.example.crocusoft_mova.presentation.dashboard.home.components.HomeHeader
import com.example.crocusoft_mova.presentation.dashboard.home.components.SectionHeader


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeContent(
    state: HomeContract.State = HomeContract.State(),
    postIntent: (HomeContract.Intent) -> Unit = {},
    onNavigateDetail: (Int) -> Unit
) {


    Scaffold(
        modifier = Modifier.navigationBarsPadding(),
        containerColor = colorResource(Colors.primary),
        topBar = {
            AppTopBar(
                modifier = Modifier.padding(BaseTheme.dimens.dp6),
                prefixIcon = Drawables.logo,
                prefixAction = {},
                prefixColor = Colors.secondary,
                suffixIcon = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(BaseTheme.dimens.dp2)
                    ) {
                        IconButton(
                            onClick = {}
                        ) {
                            Icon(
                                painter = painterResource(Drawables.icon_search),
                                contentDescription = null,
                                tint = colorResource(Colors.white)
                            )
                        }
                        IconButton(
                            onClick = {}
                        ) {
                            Icon(
                                painter = painterResource(Drawables.icon_notification),
                                contentDescription = null,
                                tint = colorResource(Colors.white)
                            )
                        }
                    }
                },
                isSuffixIconVisible = true
            )
        }

    ) {

        LazyColumn {
            item {
                HomeHeader(
                    movieUiModel = MovieUiModel.mock,
                    postIntent = {}
                )
            }
            item {
                SectionHeader(
                    title = stringResource(Strings.top_movies),
                    seeAllClick = {}
                )
            }
            item {
                LazyRow(
                    contentPadding = PaddingValues(horizontal = BaseTheme.dimens.dp6),
                    horizontalArrangement = Arrangement.spacedBy(BaseTheme.dimens.dp2)
                ) {

                    items(state.discoverMovies.take(10)) { movie ->
                        MovieCoverItem(
                            movieModel = movie,
                            onClickMovie = {
                                onNavigateDetail(movie.id)
                            }
                        )
                    }


                }
            }
            item {
                SectionHeader(
                    title = stringResource(Strings.new_releases),
                    seeAllClick = {}
                )
            }
            item {
                LazyRow(
                    contentPadding = PaddingValues(horizontal = BaseTheme.dimens.dp6),
                    horizontalArrangement = Arrangement.spacedBy(BaseTheme.dimens.dp2)
                ) {

                    items(state.upcomingMovies.take(10)) { movie ->
                        MovieCoverItem(
                            movieModel = movie,
                            onClickMovie = {
                                onNavigateDetail(movie.id)
                            }
                        )
                    }


                }
            }
            item {
                Spacer(modifier = Modifier.height(BaseTheme.dimens.bottom_padding))
            }
        }

    }


}
