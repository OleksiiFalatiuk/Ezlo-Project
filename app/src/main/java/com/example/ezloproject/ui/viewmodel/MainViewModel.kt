package com.example.ezloproject.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.viewModelScope
import com.example.ezloproject.data.model.locale.ItemEntity
import com.example.ezloproject.data.repository.ItemRemoteRepository
import com.example.ezloproject.ui.IconMapper
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: ItemRemoteRepository,
) : BaseViewModel() {
    val mainDataResult = mutableStateListOf<ItemEntity>()

    fun getItemData() {
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

    fun deleteItem(itemEntity: ItemEntity) {
        viewModelScope.launch {
            repository.deleteById(itemEntity.id).fold(
                onSuccess = { getItemData() },
                onFailure = {
                    _errorMessage.value = it.message
                }
            )
        }
    }

    fun insertItemData() {
        viewModelScope.launch {
            repository.insertItemData().fold(
                onSuccess = {
                    getItemData()
                },
                onFailure = {
                    _errorMessage.value = it.message
                }
            )
        }
    }
}