package com.example.crocusoft_mova.domain.usecases

import com.example.crocusoft_mova.core.ContentState
import com.example.crocusoft_mova.domain.repository.ChooseInterestRepository
import javax.inject.Inject

class CheckIfTagsExistUseCase @Inject constructor(private val repository: ChooseInterestRepository) {

    suspend operator fun invoke(): ContentState<Boolean> = repository.checkWhetherExist()
}