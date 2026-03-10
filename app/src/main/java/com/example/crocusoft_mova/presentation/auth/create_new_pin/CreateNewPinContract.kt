package com.example.crocusoft_mova.presentation.auth.create_new_pin

sealed interface CreateNewPinContract {

    sealed interface Intent {

        data class SetPin( val index:Int, val pin: String) : Intent

        data object Submit : Intent
    }


    data class State(
        val pin: List<String> = List(4){""},
    )
}