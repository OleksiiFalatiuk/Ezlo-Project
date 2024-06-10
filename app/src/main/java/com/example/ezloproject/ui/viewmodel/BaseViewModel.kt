package com.example.ezloproject.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {
    val errorMessage = mutableStateOf<String?>(null)
}