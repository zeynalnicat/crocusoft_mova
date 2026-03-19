package com.example.crocusoft_mova.domain.usecases

import com.example.crocusoft_mova.domain.repository.MyListRepository
import javax.inject.Inject

class FetchMyListUseCase @Inject constructor(
    private val myListRepository: MyListRepository
) {

    suspend operator fun invoke() = myListRepository.fetchMyList()
}