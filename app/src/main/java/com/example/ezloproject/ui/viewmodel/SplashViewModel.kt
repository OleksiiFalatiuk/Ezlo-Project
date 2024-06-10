package com.example.ezloproject.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.ezloproject.data.repository.ItemRemoteRepository
import kotlinx.coroutines.launch

class SplashViewModel(
    private val repository: ItemRemoteRepository
): BaseViewModel() {

    private val _isResultSuccess = MutableLiveData<Boolean?>(null)
    val isResultSuccess: LiveData<Boolean?> = _isResultSuccess

    private fun insertItemData() {
        viewModelScope.launch {
            repository.insertItemData().fold(
                onSuccess = {
                    _isResultSuccess.value = true
                },
                onFailure = {
                    _isResultSuccess.value = false
                    _errorMessage.value = it.message
                }
            )
        }
    }

    fun getItemData(){
        viewModelScope.launch {
            repository.getItemData().fold(
                onSuccess = {
                    if (it.isEmpty()) {
                        insertItemData()
                    }else {
                        _isResultSuccess.value = true
                    }
                },
                onFailure = {
                    _errorMessage.value = it.message
                }
            )
        }
    }
}