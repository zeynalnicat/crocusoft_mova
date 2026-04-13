package com.example.crocusoft_mova.domain.usecases

import com.example.crocusoft_mova.domain.models.ProfileUiModel
import com.example.crocusoft_mova.domain.repository.ProfileRepository
import javax.inject.Inject

class GetProfileInfoUseCase @Inject constructor(
    private val profileRepository: ProfileRepository
) {
    suspend operator fun invoke(): ProfileUiModel? = profileRepository.getCurrentUser()
}