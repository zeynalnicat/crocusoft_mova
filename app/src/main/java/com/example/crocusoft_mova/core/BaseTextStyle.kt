package com.example.crocusoft_mova.core

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.example.crocusoft_mova.ui.theme.Red
import com.example.crocusoft_mova.ui.theme.White

object BaseTextStyle {


    val t36Bold = TextStyle(
        color = White,
        fontSize = BaseTheme.dimens.sp12,
        fontWeight = FontWeight.W700,
    )

    val t48Bold = TextStyle(
        color = White,
        fontSize = BaseTheme.dimens.sp18,
        fontWeight = FontWeight.W700,
    )

    val t16Bold = TextStyle(
        color = White,
        fontSize = BaseTheme.dimens.sp2,
        fontWeight = FontWeight.W700
    )

    val t16BoldRed = TextStyle(
        color = Red,
        fontSize = BaseTheme.dimens.sp2,
        fontWeight = FontWeight.W700
    )

    val t16SemiBoldRed = TextStyle(
        color = Red,
        fontSize = BaseTheme.dimens.sp2,
        fontWeight = FontWeight.W500
    )

    val t18SemiBold = TextStyle(
        color = White,
        fontSize = BaseTheme.dimens.sp3,
        fontWeight = FontWeight.W600
    )

    val t14SemiBold = TextStyle(
        color = White,
        fontSize = BaseTheme.dimens.sp1,
        fontWeight = FontWeight.W600
    )

    val t24Bold = TextStyle(
        color = White,
        fontSize = BaseTheme.dimens.sp6,
        fontWeight = FontWeight.W700
    )
}