package com.example.crocusoft_mova.presentation.dashboard.explore

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import com.example.crocusoft_mova.common.components.MovieCoverItem
import com.example.crocusoft_mova.common.components.NotAnyComponent
import com.example.crocusoft_mova.common.components.VerticalSpacer
import com.example.crocusoft_mova.core.BaseTheme
import com.example.crocusoft_mova.core.Colors
import com.example.crocusoft_mova.core.Drawables
import com.example.crocusoft_mova.core.Strings
import com.example.crocusoft_mova.presentation.dashboard.explore.components.ExploreHeader
import com.example.crocusoft_mova.presentation.dashboard.explore.components.ExploreMovieItem
import com.example.crocusoft_mova.presentation.dashboard.explore.components.FilterDesignContent



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExploreContent(
    state: ExploreContract.State,
    postIntent: (ExploreContract.Intent) -> Unit,
    onNavigateDetail: (Int) -> Unit,

    ) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(BaseTheme.dimens.dp6)

    ) {
        ExploreHeader(
            value = state.searchQuery,
            onValueChange = {
                postIntent(ExploreContract.Intent.SetQuery(it))
            },
            onFilterClick = { postIntent(ExploreContract.Intent.ToggleFilterSheet(true))}
        )

        VerticalSpacer(BaseTheme.dimens.dp6)

        when {
            state.movies.isEmpty() && state.searchQuery.isNotEmpty() -> {
                NotAnyComponent(
                    imageRes = Drawables.not_found,
                    title = stringResource(Strings.not_found),
                    description = stringResource(Strings.not_found_description)
                )
            }

            state.searchQuery.isEmpty() -> {
                Text(
                    text = stringResource(Strings.top_searches),
                    style = BaseTheme.textStyle.t16Bold
                )
                VerticalSpacer(BaseTheme.dimens.dp6)
                LazyVerticalGrid(
                    verticalArrangement = Arrangement.spacedBy(BaseTheme.dimens.dp2),
                    horizontalArrangement = Arrangement.spacedBy(BaseTheme.dimens.dp2),
                    modifier = Modifier.fillMaxWidth(),
                    columns = androidx.compose.foundation.lazy.grid.GridCells.Fixed(2),

                    ) {
                    items(
                        count = state.movies.size,
                        key = { state.movies[it].id }
                    ) {
                        MovieCoverItem(
                            movieModel = state.movies[it],
                            onClickMovie = onNavigateDetail
                        )
                    }
                    item {
                        VerticalSpacer(BaseTheme.dimens.dp12)
                    }
                }

            }

            else -> {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(BaseTheme.dimens.dp3)
                ) {

                    items(
                        count = state.movies.size,
                        key = { state.movies[it].id }
                    ) {
                        ExploreMovieItem(
                            movieUiModel = state.movies[it],
                            onClick = onNavigateDetail
                        )

                        if (it == state.movies.size - 1) {
                            VerticalSpacer(BaseTheme.dimens.dp15)
                        }
                    }
                }


            }

        }
        if (state.isVisible) {
            ModalBottomSheet(
                sheetState = sheetState,
                onDismissRequest = {
                    postIntent(ExploreContract.Intent.ToggleFilterSheet(false))
                },
                containerColor = colorResource(Colors.dark),
                shape = RoundedCornerShape(topStart = BaseTheme.dimens.dp10 , topEnd = BaseTheme.dimens.dp10)
            ) {
                FilterDesignContent()
            }
        }
    }
}
