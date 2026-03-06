package com.example.crocusoft_mova.common.components

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import coil3.Image
import coil3.compose.AsyncImage
import com.example.crocusoft_mova.core.BaseTheme
import com.example.crocusoft_mova.core.Colors
import com.example.crocusoft_mova.core.Drawables
import java.net.URI


@Composable
fun ProfileAvatar(
    onClick: (Uri) -> Unit = {},
    avatar: Uri?,
) {


    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri: Uri? ->
            onClick(uri ?: Uri.EMPTY)
        }
    )

    Box(
        modifier = Modifier.fillMaxSize()
    ) {


        Box(
            modifier = Modifier.align(alignment = Alignment.Center)
        ) {

            if (avatar == null) {
                Image(
                    painter = painterResource(Drawables.profile_avatar),
                    contentDescription = null,
                    modifier = Modifier
                        .align(alignment = Alignment.Center)
                        .size(BaseTheme.dimens.avatar_size)
                )

            } else {
                Box(
                    modifier = Modifier.align(alignment = Alignment.Center)
                ) {
                    AsyncImage(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(BaseTheme.dimens.avatar_size)
                            .clip(CircleShape),
                        model = avatar,
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )

                }
            }



            IconButton(
                modifier = Modifier.align(Alignment.BottomEnd),
                onClick = { launcher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)) }
            ) {
                Icon(
                    painter = painterResource(Drawables.icon_edit),
                    contentDescription = null,
                    tint = colorResource(
                        Colors.secondary
                    )
                )
            }

        }


    }


}