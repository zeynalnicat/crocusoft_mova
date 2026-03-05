package com.example.crocusoft_mova.common.components

import android.view.RoundedCorner
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import com.example.crocusoft_mova.core.BaseTheme
import com.example.crocusoft_mova.core.Colors


@Composable
fun AppIconButton(

    icon:Int,
    onClick: ()->Unit ,
){

    Box(
         modifier = Modifier
             .clip(RoundedCornerShape(BaseTheme.dimens.dp4))
             .border(
                 border = BorderStroke(width = BaseTheme.dimens.dp01, color = colorResource(Colors.dark2))
             )
             .background(color = colorResource(Colors.primary))
    ){
        Icon(
            modifier = Modifier.align(alignment = Alignment.Center)
                .padding(BaseTheme.dimens.dp4),
            painter = painterResource(icon),
            contentDescription = null
        )
    }
}