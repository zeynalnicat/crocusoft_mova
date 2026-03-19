package com.example.crocusoft_mova.core

abstract class CoreState {

    fun setState(state: CoreState): CoreState {
        return this
    }
}
