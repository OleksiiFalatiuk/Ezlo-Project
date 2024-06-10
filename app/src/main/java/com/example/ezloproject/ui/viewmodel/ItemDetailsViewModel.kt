package com.example.ezloproject.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.ezloproject.Constants
import com.example.ezloproject.data.model.local.ItemEntity
import com.example.ezloproject.data.repository.ItemRepository
import kotlinx.coroutines.launch

class ItemDetailsViewModel(
    private val repository: ItemRepository
) : BaseViewModel() {

    val itemResult = mutableStateOf<ItemEntity?>(null)
    val updatedTitle = mutableStateOf("")

    fun getItemById(id: Int) {
        viewModelScope.launch {
            repository.getItemData(id).fold(
                onSuccess = {
                    updatedTitle.value = it?.title ?: ""
                    itemResult.value = it
                },
                onFailure = {
                    errorMessage.value = it.message
                }
            )
        }
    }

    fun updateItemTitle(title: String?, itemId: Int?) {
        viewModelScope.launch {
            if (title != null && itemId != null) {
                repository.updateItemTitle(title, itemId).fold(
                    onSuccess = {

                    },
                    onFailure = {
                        errorMessage.value = it.message
                    }
                )
            } else {
                errorMessage.value = Constants.INCORRECT_DATA_MESSAGE
            }
        }
    }
}