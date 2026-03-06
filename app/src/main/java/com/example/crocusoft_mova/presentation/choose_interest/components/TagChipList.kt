package com.example.crocusoft_mova.presentation.choose_interest.components

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import com.example.crocusoft_mova.common.components.TagChip
import com.example.crocusoft_mova.core.BaseTheme
import com.example.crocusoft_mova.domain.tag.TagEntity
import com.example.crocusoft_mova.presentation.choose_interest.ChooseInterestContract

@Composable
fun TagChipList(
    tags: List<TagEntity>,
    selectedTags: Set<TagEntity>,
    postIntent: (ChooseInterestContract.Intent) -> Unit
) {

    LazyVerticalGrid(
        columns = GridCells.Adaptive(BaseTheme.dimens.min_width),
    ) {
        items(
            count = tags.size,
            key = { tags[it].name }
        ) {
            TagChip(
                text = tags[it].name,
                onChipAction = { postIntent(ChooseInterestContract.Intent.ToggleTag(tags[it])) },
                isSelected = selectedTags.contains(tags[it])
            )
        }
    }

}