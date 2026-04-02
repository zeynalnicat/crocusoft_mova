package com.example.crocusoft_mova.presentation.dashboard.movie_detail.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.crocusoft_mova.core.BaseTheme
import com.example.crocusoft_mova.core.Colors

@Composable
fun DetailTabRow(
    modifier: Modifier = Modifier,
    selectedTab: Int,
    onTabSelected: (Int) -> Unit
) {
    val tabs = listOf("Trailers", "More Like This", "Comments")

    TabRow(
        modifier = modifier.padding(horizontal = BaseTheme.dimens.dp6),
        selectedTabIndex = selectedTab,
        containerColor = colorResource(Colors.primary),
        contentColor = colorResource(Colors.gray),
        indicator = { tabPositions ->

            TabRowDefaults.Indicator(
                Modifier.tabIndicatorOffset(tabPositions[selectedTab]),
                color = colorResource(Colors.secondary),
                height = 3.dp
            )
        },
        divider = {
            HorizontalDivider(
                //modifier = Modifier.padding(horizontal = 16.dp),
                thickness = BaseTheme.dimens.dp02,
                color = colorResource(Colors.dark_gray)
            )
        }
    ) {
        tabs.forEachIndexed { index, title ->
            val isSelected = selectedTab == index
            Tab(
                selected = isSelected,
                onClick = { onTabSelected(index) },
                text = {
                    Text(
                        text = title,
                        style = if (isSelected) BaseTheme.textStyle.t16BoldRed.copy(fontWeight = FontWeight.W600)
                        else BaseTheme.textStyle.t16Bold.copy(color = colorResource(Colors.dark_gray),
                            fontWeight = FontWeight.W600),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            )
        }
    }
}