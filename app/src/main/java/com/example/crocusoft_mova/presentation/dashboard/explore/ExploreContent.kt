package com.example.crocusoft_mova.presentation.dashboard.explore

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.crocusoft_mova.common.components.AppTextField
import com.example.crocusoft_mova.common.components.NotAnyComponent
import com.example.crocusoft_mova.core.BaseTheme
import com.example.crocusoft_mova.core.Drawables
import com.example.crocusoft_mova.core.Strings


@Composable
fun ExploreContent(
    state: ExploreContract.State,
    postIntent: (ExploreContract.Intent) -> Unit
) {


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(BaseTheme.dimens.dp6)

    ) {
        AppTextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.searchQuery,
            onValueChange = { postIntent(ExploreContract.Intent.SetQuery((it))) },
            prefixIcon = Drawables.icon_search,
            placeholder = stringResource(Strings.search)
        )

        if (state.movies.isEmpty()) {
            NotAnyComponent(
                imageRes = Drawables.not_found,
                title = stringResource(Strings.not_found),
                description = stringResource(Strings.not_found_description)
            )
        }
    }
}