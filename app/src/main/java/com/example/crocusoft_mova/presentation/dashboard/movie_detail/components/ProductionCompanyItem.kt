package com.example.crocusoft_mova.presentation.dashboard.movie_detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import coil3.compose.AsyncImage
import com.example.crocusoft_mova.core.BaseTheme
import com.example.crocusoft_mova.core.Colors


@Composable
fun ProductionCompanyItem(
    model: String,
    title: String,
) {


    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(BaseTheme.dimens.dp3)
    ) {
        Box(
            modifier = Modifier
                .size(BaseTheme.dimens.dp10)
                .clip(CircleShape)
                .background(color = colorResource(Colors.primary))
        ) {
            AsyncImage(
                model = model,
                contentDescription = null,
                modifier = Modifier.size(BaseTheme.dimens.dp10)
            )
        }

        Text(
            text = title,
            style = BaseTheme.textStyle.t10Bold.copy(color = colorResource(Colors.white))
        )
    }


}