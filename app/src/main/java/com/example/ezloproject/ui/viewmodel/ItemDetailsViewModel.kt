package com.example.ezloproject.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.ezloproject.data.model.locale.ItemEntity
import com.example.ezloproject.data.repository.ItemRemoteRepository
import kotlinx.coroutines.launch

class ItemDetailsViewModel(
    private val repository: ItemRemoteRepository
) : BaseViewModel() {

    val itemResult = mutableStateOf<ItemEntity?>(null)

    fun getItemById(id: Int) {
        viewModelScope.launch {
            repository.getItemData(id).fold(
                onSuccess = {
                    itemResult.value = it
                },
                onFailure = {
                    _errorMessage.value = it.message
                }
            )
        }
    }

    fun updateItemTitle(title: String?, itemId: Int?) {
        viewModelScope.launch {
            if (title != null && itemId != null) {
                repository.updateItemTitle(title, itemId)
            } else {
                _errorMessage.value = "Something went wrong"
            }
        }
    }
}