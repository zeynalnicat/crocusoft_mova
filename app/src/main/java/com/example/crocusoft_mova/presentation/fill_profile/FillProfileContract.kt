package com.example.crocusoft_mova.presentation.fill_profile

import android.net.Uri

sealed interface FillProfileContract {

    sealed interface Intent {
        data class SetFullName(val fullName: String) : Intent
        data class SetNickName(val nickName: String) : Intent
        data class SetEmail(val email: String) : Intent
        data class SetPhoneNumber(val phoneNumber:String):Intent
        data class SetGender(val gender:String):Intent

        data class SetProfile(val uri:Uri):Intent
    }


    data class State(
        val fullName: String = "",
        val nickName: String = "",
        val email: String = "",
        val phoneNumber:String = "",
        val gender: String = "",
        val imgUri: Uri? = null
    )
}