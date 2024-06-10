package com.example.ezloproject.data.repository

import com.example.ezloproject.data.model.local.ItemEntity

interface ItemRepository {
    suspend fun insertItemsData(): Result<Unit>
    suspend fun getItemsData(): Result<List<ItemEntity>>
    suspend fun getItemData(id: Int): Result<ItemEntity?>
    suspend fun updateItemTitle(title: String, itemId: Int): Result<Unit>
    suspend fun deleteById(id: Int): Result<Unit>
}