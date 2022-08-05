package com.example.globoplay.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SplashScreenViewModel:ViewModel() {
    private val _isloading = MutableStateFlow(true)
     val isLoading = _isloading.asStateFlow()

    init {
        viewModelScope.launch {
            delay(1000)
            _isloading.value = false
        }
    }
}