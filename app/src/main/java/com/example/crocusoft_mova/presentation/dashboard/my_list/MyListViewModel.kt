package com.example.crocusoft_mova.presentation.dashboard.my_list

import android.view.View
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MyListViewModel: ViewModel() {

    private val _state = MutableStateFlow(MyListContract.State())
    val state = _state.asStateFlow()

    fun onIntent(intent: MyListContract.Intent){

    }


}