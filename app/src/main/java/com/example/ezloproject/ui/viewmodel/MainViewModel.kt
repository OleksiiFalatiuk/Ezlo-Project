package com.example.ezloproject.ui.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.viewModelScope
import com.example.ezloproject.data.model.locale.ItemEntity
import com.example.ezloproject.data.repository.ItemRemoteRepository
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: ItemRemoteRepository
) : BaseViewModel() {
    val mainDataResult = mutableStateListOf<ItemEntity>()

    fun getItemData(){
        viewModelScope.launch {
            repository.getItemData().fold(
                onSuccess = {
                    mainDataResult.clear()
                    mainDataResult.addAll(it)
                },
                onFailure = {
                    _errorMessage.value = it.message
                }
            )
        }
    }
}