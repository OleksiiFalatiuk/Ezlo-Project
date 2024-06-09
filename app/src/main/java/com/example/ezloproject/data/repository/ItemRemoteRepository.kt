package com.example.ezloproject.data.repository

import com.example.ezloproject.data.locale.ItemDao
import com.example.ezloproject.data.model.locale.ItemEntity
import com.example.ezloproject.data.remote.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ItemRemoteRepository(private val apiService: ApiService, private val itemDao: ItemDao) {
    suspend fun insertItemData(): Result<Unit> {
        return withContext(Dispatchers.IO) {
            val result = runCatching { apiService.getItems() }
            result.fold(
                { response ->
                    Result.success(
                        itemDao.insertAll(
                            response.devices.map {
                                ItemEntity(
                                    pkDevice = it?.pkDevice ?: 0,
                                    platform = it?.platform ?: "",
                                )
                            }
                        )
                    )
                }, {
                    Result.failure(it)
                }
            )
        }
    }

    suspend fun getItemData(): Result<List<ItemEntity>> {
        return withContext(Dispatchers.IO) {
            val result = runCatching { itemDao.getAllItems() }
            result.fold(
                {
                    Result.success(it)
                }, {
                    Result.failure(it)
                }
            )
        }
    }
}