package com.example.crocusoft_mova.data.service.remote

import com.example.crocusoft_mova.data.service.remote.model.ResponseModel

interface ApiService {


    suspend fun fetchDiscoverMovies(): ResponseModel

    suspend fun fetchDiscoverTv(): ResponseModel
}