package com.example.nike

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    private val _isLoading = mutableStateOf(true)
    val isLoading = _isLoading
    init {
        viewModelScope.launch {
            delay(3000)
            _isLoading.value = false

        }
    }
}