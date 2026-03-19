package com.example.crocusoft_mova.presentation.dashboard.my_list

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.crocusoft_mova.common.components.AppTopBar
import com.example.crocusoft_mova.common.components.MovieCoverItem
import com.example.crocusoft_mova.common.components.NotAnyComponent
import com.example.crocusoft_mova.core.BaseTheme
import com.example.crocusoft_mova.core.Colors
import com.example.crocusoft_mova.core.Drawables
import com.example.crocusoft_mova.core.Strings

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MyListContent(
    state: MyListContract.State,
    postIntent: (MyListContract.Intent) -> Unit,
    onNavigatePostDetail: (Int) -> Unit,
) {

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            AppTopBar(
                modifier = Modifier
                    .padding(top = BaseTheme.dimens.dp2)
                    .statusBarsPadding(),
                prefixIcon = Drawables.logo,
                prefixColor = Colors.secondary,
                title = stringResource(Strings.my_list),
                prefixAction = {},
                isSuffixIconVisible = true,
                suffixIcon = {
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(
                            painter = painterResource(Drawables.icon_search),
                            contentDescription = null,
                            tint = colorResource(Colors.white)
                        )
                    }
                }

            )
        },
        containerColor = colorResource(Colors.primary)
    ) { innerPadding->

        if (state.movies.isEmpty()) {

            NotAnyComponent(
                imageRes = Drawables.empty_list,
                title = stringResource(Strings.empty_list),
                description = stringResource(Strings.empty_list_description)
            )

        } else {
            LazyVerticalGrid(
                verticalArrangement = Arrangement.spacedBy(BaseTheme.dimens.dp2),
                horizontalArrangement = Arrangement.spacedBy(BaseTheme.dimens.dp2),
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxWidth()
                    .statusBarsPadding()
                    .navigationBarsPadding(),
                columns = GridCells.Fixed(2),
            ) {
                items(
                    state.movies.size,
                    itemContent = { index ->
                        MovieCoverItem(
                            movieModel = state.movies[index],
                            onClickMovie = onNavigatePostDetail
                        )

                    }
                )
            }


        }
    }
}

