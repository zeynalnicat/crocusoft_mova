package com.example.crocusoft_mova.presentation.dashboard.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import com.example.crocusoft_mova.common.components.AppTopBar
import com.example.crocusoft_mova.core.BaseTheme
import com.example.crocusoft_mova.core.Colors
import com.example.crocusoft_mova.core.Drawables
import com.example.crocusoft_mova.domain.models.MovieUiModel
import com.example.crocusoft_mova.presentation.dashboard.home.components.HomeHeader


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeContent() {

    Scaffold(
        contentColor = colorResource(Colors.primary),
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
                    movieUiModel = MovieUiModel(
                        0,
                        "Doctor Strange 2",
                        "",
                        "Action, Superhero, Science Fiction, ...",
                        0.0,
                        image = "https://tr.web.img2.acsta.net/pictures/22/05/05/14/02/4321698.jpg"
                    ),
                    postIntent = {}
                )
            }

        }


    }
}