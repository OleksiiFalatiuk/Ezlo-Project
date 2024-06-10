package com.example.ezloproject.ui.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.viewModelScope
import com.example.ezloproject.data.model.local.ItemEntity
import com.example.ezloproject.data.repository.ItemRepository
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: ItemRepository,
) : BaseViewModel() {
    val mainDataResult = mutableStateListOf<ItemEntity>()

    fun deleteItem(itemEntity: ItemEntity) {
        viewModelScope.launch {
            repository.deleteById(itemEntity.id).fold(
                onSuccess = { getItemData() },
                onFailure = {
                    errorMessage.value = it.message
                }
            )
        }
    }

    fun insertItemData() {
        viewModelScope.launch {
            repository.insertItemsData().fold(
                onSuccess = {
                    getItemData()
                },
                onFailure = {
                    errorMessage.value = it.message
                }
            )
        }
    }

    fun getItemData() {
        viewModelScope.launch {
            repository.getItemsData().fold(
                onSuccess = {
                    if (it.isEmpty()) {
                        insertItemData()
                    } else {
                        mainDataResult.clear()
                        mainDataResult.addAll(it)
                    }
                },
                onFailure = {
                    errorMessage.value = it.message
                }
            )
        }
    }
}