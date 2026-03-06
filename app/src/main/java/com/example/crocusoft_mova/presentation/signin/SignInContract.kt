package com.example.crocusoft_mova.presentation.signin

sealed interface SignInContract {

    sealed interface Intent {

        data object Submit: Intent

        data class SetEmail(val email:String): Intent

        data class SetPassword(val password:String):Intent

        data class SetChecked(val checked: Boolean):Intent


    }

    data class State(
        val email: String = "",
        val password: String = "",
        val checked: Boolean = false,
        val isLoading: Boolean = false,

        )
}