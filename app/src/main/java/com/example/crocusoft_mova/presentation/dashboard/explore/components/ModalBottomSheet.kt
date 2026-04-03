package com.example.crocusoft_mova.presentation.dashboard.explore.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.crocusoft_mova.common.components.AppButton
import com.example.crocusoft_mova.common.components.VerticalSpacer
import com.example.crocusoft_mova.core.BaseTheme
import com.example.crocusoft_mova.core.Colors
import com.example.crocusoft_mova.core.Strings

@Composable
fun FilterDesignContent(

) {
    val categories = listOf("Movie", "TV Shows", "K-Drama", "Anime")
    val regions = listOf("All Regions", "US", "South Korea", "China")
    val genres = listOf("Action", "Comedy", "Romance", "Thriller")
    val periods = listOf("All Periods", "2022", "2021", "2020")
    val sortOptions = listOf("Popularity", "Latest Release")
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = BaseTheme.dimens.dp6),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = stringResource(Strings.sort_filter),
            style = BaseTheme.textStyle.t24Bold.copy(color = colorResource(Colors.light_red)),
        )

        VerticalSpacer(BaseTheme.dimens.dp5)

        HorizontalDivider(
            thickness = 1.dp,
        )
        VerticalSpacer(BaseTheme.dimens.dp5)

        FilterRow(
            title = "Categories",
            items = categories,
            selectedItem = "TV Shows",
            onItemSelected = { }
        )
        VerticalSpacer(BaseTheme.dimens.dp5)

        FilterRow(
            title = "Regions",
            items = regions,
            selectedItem = "All Regions",
            onItemSelected = { }
        )

        VerticalSpacer(BaseTheme.dimens.dp5)

        FilterRow(
            title = "Genre",
            items = genres,
            selectedItem = "TV Shows",
            onItemSelected = { }
        )
        VerticalSpacer(BaseTheme.dimens.dp5)

        FilterRow(
            title = "Time/Periods",
            items = periods,
            selectedItem = "2022",
            onItemSelected = { }
        )
        VerticalSpacer(BaseTheme.dimens.dp5)

        FilterRow(
            title = "Sorts",
            items = sortOptions,
            selectedItem = "Popularity",
            onItemSelected = { }
        )


        HorizontalDivider(
            modifier = Modifier.padding(vertical = BaseTheme.dimens.dp6),
            thickness = 1.dp,
        )
    }

        Row(
            modifier = Modifier.fillMaxSize().padding(horizontal = BaseTheme.dimens.dp6),
            horizontalArrangement = Arrangement.spacedBy(BaseTheme.dimens.dp6)
        ) {
            AppButton(
                modifier = Modifier.weight(1f),
                color = Color.Gray,
                text = stringResource(Strings.reset),
                action = { }
            )
            AppButton(
                modifier = Modifier.weight(1f),
                text = stringResource(Strings.apply),
                action = { }
            )
        }

    }


