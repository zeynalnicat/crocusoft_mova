package com.example.crocusoft_mova.presentation.dashboard.home.components

import android.R
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.crocusoft_mova.core.BaseTheme
import com.example.crocusoft_mova.core.Colors

@Composable
fun SectionHeader(
    title: String,
    seeAllClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(BaseTheme.dimens.dp6),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = title, style = BaseTheme.textStyle.t20Bold)
        TextButton(onClick = seeAllClick) {
            Text(text = "See all", style = BaseTheme.textStyle.t14)
        }
    }
}
