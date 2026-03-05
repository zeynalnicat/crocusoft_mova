package com.example.crocusoft_mova.common.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.crocusoft_mova.core.BaseTheme
import com.example.crocusoft_mova.core.Colors


@Composable
fun AppButton(
    action: ()->Unit,
    text:String,

){

    Button(
        modifier = Modifier.fillMaxWidth()
        ,
        shape = RoundedCornerShape(BaseTheme.dimens.dp4),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(Colors.secondary)
        )
        ,
        onClick = action
    ) {
        Text(
            text = text,
            style = BaseTheme.textStyle.t16Bold
        )
    }

}


@Preview
@Composable
fun AppButtonPreview(){

    AppButton(
        action = {},
        text = "Submit"
    )
}