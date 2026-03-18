package com.example.crocusoft_mova.common.components


import android.R
import android.graphics.Paint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.crocusoft_mova.core.BaseTheme
import com.example.crocusoft_mova.core.Colors
import com.example.crocusoft_mova.core.Drawables
import com.example.crocusoft_mova.data.mappers.toUiModel
import com.example.crocusoft_mova.data.service.remote.model.MovieModel
import com.example.crocusoft_mova.domain.models.MovieUiModel


@Composable
fun MovieCoverItem(
   movieModel: MovieUiModel,
   onClickMovie : ()->Unit
) {

    Box(
        modifier = Modifier
            .width(width = BaseTheme.dimens.movie_cover_width)
            .height(BaseTheme.dimens.movie_cover_height)
            .clip(RoundedCornerShape(12.dp))
    ) {
        AsyncImage(
            model = movieModel.image,
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Box(modifier = Modifier.align(Alignment.TopStart).padding(BaseTheme.dimens.dp3),
            contentAlignment = Alignment.Center){
            Box(modifier = Modifier
                .width(BaseTheme.dimens.rate_box_width)
                .height(BaseTheme.dimens.rate_box_height)
                .clip(RoundedCornerShape(BaseTheme.dimens.dp06))
                .background(colorResource(Colors.secondary)))

            Text(text = "${movieModel.vote_average}", style = BaseTheme.textStyle.t10,
                color = colorResource(Colors.white),
                textAlign = TextAlign.Center)
        }
    }

}
@Preview
@Composable
fun AppCoverItemPreview() {

}