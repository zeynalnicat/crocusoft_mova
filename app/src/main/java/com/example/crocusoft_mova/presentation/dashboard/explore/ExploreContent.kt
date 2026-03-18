package com.example.crocusoft_mova.presentation.dashboard.explore

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.crocusoft_mova.common.components.AppTextField
import com.example.crocusoft_mova.common.components.NotAnyComponent
import com.example.crocusoft_mova.common.components.VerticalSpacer
import com.example.crocusoft_mova.core.BaseTheme
import com.example.crocusoft_mova.core.Drawables
import com.example.crocusoft_mova.core.Strings
import com.example.crocusoft_mova.presentation.dashboard.explore.components.ExploreMovieItem
import kotlinx.coroutines.delay


@Composable
fun ExploreContent(
    state: ExploreContract.State,
    postIntent: (ExploreContract.Intent) -> Unit
) {


    LaunchedEffect(state.searchQuery) {
        delay(400)
        postIntent(ExploreContract.Intent.Search)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(BaseTheme.dimens.dp6)

    ) {
        AppTextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.searchQuery,
            onValueChange = {
                postIntent(ExploreContract.Intent.SetQuery((it)))
            },
            prefixIcon = Drawables.icon_search,
            placeholder = stringResource(Strings.search)
        )

        VerticalSpacer(BaseTheme.dimens.dp6)

        if (state.movies.isEmpty() && state.searchQuery.isNotEmpty()) {
            NotAnyComponent(
                imageRes = Drawables.not_found,
                title = stringResource(Strings.not_found),
                description = stringResource(Strings.not_found_description)
            )
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(BaseTheme.dimens.dp3)
            ) {

                if (state.searchQuery.isEmpty()) {
                    item {
                        Text(
                            text = stringResource(Strings.trending),
                            style = BaseTheme.textStyle.t16Bold
                        )
                    }

                    item {
                        VerticalSpacer(BaseTheme.dimens.dp1)
                    }
                }

                items(
                    count = state.movies.size,
                    key = { state.movies[it].id }
                ) {
                    ExploreMovieItem(
                        movieUiModel = state.movies[it]
                    )

                    if (it == state.movies.size - 1) {
                        VerticalSpacer(BaseTheme.dimens.dp15)
                    }
                }
            }
        }


    }

}