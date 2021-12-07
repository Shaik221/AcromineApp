package com.example.acromineapp.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.acromineapp.network.AcronymApi

@Suppress("UNCHECKED_CAST")
class MainActivityViewModelFactory(private val api: AcronymApi) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)) {
            return MainActivityViewModel(api) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}