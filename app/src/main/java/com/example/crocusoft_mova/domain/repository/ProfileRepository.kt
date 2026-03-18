package com.example.crocusoft_mova.domain.repository

import com.example.crocusoft_mova.core.ContentState

interface ProfileRepository {

    suspend fun logOut(): ContentState<Unit>
}