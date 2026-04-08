package com.example.crocusoft_mova.presentation.dashboard.explore.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.crocusoft_mova.common.components.MovieCoverItem
import com.example.crocusoft_mova.common.components.VerticalSpacer
import com.example.crocusoft_mova.core.BaseTheme
import com.example.crocusoft_mova.core.Strings
import com.example.crocusoft_mova.presentation.dashboard.explore.ExploreContract
import com.example.crocusoft_mova.presentation.dashboard.explore.isAnyFilteredApplied

@Composable
fun GridResultsSection(
    state: ExploreContract.State,
    onNavigateDetail: (Int) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        if (state.isAnyFilteredApplied()) {
            FilteredResultsList(state)
        } else {
            Text(
                text = stringResource(Strings.top_searches),
                style = BaseTheme.textStyle.t16Bold,
                modifier = Modifier.padding(start = BaseTheme.dimens.dp6)
            )
        }

        VerticalSpacer(BaseTheme.dimens.dp6)

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(BaseTheme.dimens.dp2),
            horizontalArrangement = Arrangement.spacedBy(BaseTheme.dimens.dp2),
            contentPadding = PaddingValues(bottom = BaseTheme.dimens.dp12),
            modifier = Modifier.fillMaxWidth().padding(horizontal = BaseTheme.dimens.dp6)
        ) {
            items(items = state.movies, key = {it.id}) { movie ->
                MovieCoverItem(movieModel = movie, onClickMovie = onNavigateDetail)
            }
        }
    }
}