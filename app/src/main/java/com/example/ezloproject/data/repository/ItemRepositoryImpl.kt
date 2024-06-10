package com.example.ezloproject.data.repository

import com.example.ezloproject.data.local.ItemDao
import com.example.ezloproject.data.model.local.ItemEntity
import com.example.ezloproject.data.model.remote.DevicesResponse
import com.example.ezloproject.data.remote.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class ItemRepositoryImpl(
    private val apiService: ApiService,
    private val itemDao: ItemDao
) : ItemRepository {
    override suspend fun insertItemsData(): Result<Unit> {
        delay(1000)
        return executeWithResultHandling {
            val response = apiService.getItems()
            itemDao.deleteAll()
            itemDao.insertAll(mapItem(response))
        }
    }

    override suspend fun getItemsData(): Result<List<ItemEntity>> {
        return executeWithResultHandling {
            itemDao.getAllItems()
        }
    }

    override suspend fun getItemData(id: Int): Result<ItemEntity?> {
        return executeWithResultHandling {
            itemDao.getItemById(id)
        }
    }

    override suspend fun updateItemTitle(title: String, itemId: Int): Result<Unit> {
        return executeWithResultHandling {
            itemDao.updateItemTitle(title, itemId)
        }
    }

    override suspend fun deleteById(id: Int): Result<Unit> {
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
                title = "Home number ${(1..100).random()}"
            )
        }
    }

    private suspend fun <T> executeWithResultHandling(block: suspend () -> T): Result<T> {
        return withContext(Dispatchers.IO) {
            runCatching { block() }
        }
    }
}