package com.example.ezloproject.data.repository

import com.example.ezloproject.data.locale.ItemDao
import com.example.ezloproject.data.model.locale.ItemEntity
import com.example.ezloproject.data.model.remote.DevicesResponse
import com.example.ezloproject.data.remote.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ItemRemoteRepository(private val apiService: ApiService, private val itemDao: ItemDao) {
    suspend fun insertItemData(): Result<Unit> {
        return executeWithResultHandling {
            val response = apiService.getItems()
            itemDao.insertAll(mapItem(response))
        }
    }

    suspend fun getItemData(): Result<List<ItemEntity>> {
        return executeWithResultHandling {
            itemDao.getAllItems()
        }
    }

    suspend fun getItemData(id: Int): Result<ItemEntity?> {
        return executeWithResultHandling {
            itemDao.getItemById(id)
        }
    }

    suspend fun updateItemTitle(title: String, itemId: Int): Result<Unit> {
        return executeWithResultHandling {
            itemDao.updateItemTitle(title, itemId)
        }
    }

    suspend fun deleteById(id: Int): Result<Unit> {
        return executeWithResultHandling {
            itemDao.delete(id)
        }
    }

    private fun mapItem(response: DevicesResponse): List<ItemEntity> {
        return response.devices.map {
            ItemEntity(
                pkDevice = it?.pkDevice ?: 0,
                platform = it?.platform ?: "",
                macAddress = it?.macAddress ?: "",
                firmware = it?.firmware ?: "",
                title = ""
            )
        }
    }

    private suspend fun <T> executeWithResultHandling(block: suspend () -> T): Result<T> {
        return withContext(Dispatchers.IO) {
            runCatching { block() }
        }
    }
}