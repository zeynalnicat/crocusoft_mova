package com.example.crocusoft_mova.common.components

import android.view.RoundedCorner
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.crocusoft_mova.core.BaseTheme
import com.example.crocusoft_mova.core.Colors
import com.example.crocusoft_mova.core.Drawables


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
             .padding(horizontal = BaseTheme.dimens.dp8, vertical = BaseTheme.dimens.dp4Half)
             .clickable(
                 onClick = onClick

             )
    ){
        Image(
            modifier = Modifier.align(alignment = Alignment.Center).size(BaseTheme.dimens.dp6),
            painter = painterResource(icon),
            contentDescription = null
        )
    }
}


@Composable
@Preview
fun AppIconButtonPreview(){
    AppIconButton(
        Drawables.facebook
    ) { }
}