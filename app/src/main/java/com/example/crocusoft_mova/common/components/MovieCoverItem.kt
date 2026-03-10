package com.example.crocusoft_mova.common.components


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.crocusoft_mova.core.BaseTheme
import com.example.crocusoft_mova.data.service.remote.MovieModel


@Composable
fun MovieCoverItem(
    movieModel: MovieModel
) {

    Box(
        modifier = Modifier
            .width(width = BaseTheme.dimens.movie_cover_width)
            .height(BaseTheme.dimens.movie_cover_height)

    ) {

         Box(
             modifier = Modifier
                 .align(
                 Alignment.TopStart
             )


         )
    }

}