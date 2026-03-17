package com.example.crocusoft_mova.domain.usecases

import com.example.crocusoft_mova.domain.repository.HomeRepository
import jakarta.inject.Inject

class FetchDiscoverTVUseCase @Inject constructor(private val repository: HomeRepository) {

    suspend operator fun invoke() = repository.fetchDiscoverTv()

}