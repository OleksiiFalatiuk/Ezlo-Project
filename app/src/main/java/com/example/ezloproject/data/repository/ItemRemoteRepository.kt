package com.example.ezloproject.data.repository

import com.example.ezloproject.data.locale.ItemDao
import com.example.ezloproject.data.model.locale.ItemEntity
import com.example.ezloproject.data.model.remote.DeviceDataResponse
import com.example.ezloproject.data.model.remote.DevicesResponse
import com.example.ezloproject.data.remote.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ItemRemoteRepository(private val apiService: ApiService, private val itemDao: ItemDao) {
    suspend fun insertItemData(): Result<Unit> {
        return executeWithResultHandling {
            val response =  mockDevicesResponse
//                apiService.getItems()
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

    // Mock data
    val mockDevicesResponse = DevicesResponse(
        devices = listOf(
            DeviceDataResponse(
                pkDevice = 45013855,
                macAddress = "e0:60:66:02:e2:1b",
                pKDeviceType = 1,
                pKDeviceSubType = 2,
                firmware = "1.7.455",
                serverDevice = "vera-us-oem-device12.mios.com",
                serverEvent = "vera-us-oem-event12.mios.com",
                serverAccount = "vera-us-oem-account12.mios.com",
                internalIp = "192.168.6.213",
                lastAliveReported = "2018-02-20 04:17:43",
                platform = "Sercomm NA301"
            ),
            DeviceDataResponse(
                pkDevice = 50100057,
                macAddress = "94:4a:0c:25:63:c4",
                pKDeviceType = 1,
                pKDeviceSubType = 2,
                firmware = "1.7.6255",
                serverDevice = "vera-us-oem-device12.mios.com",
                serverEvent = "vera-us-oem-event12.mios.com",
                serverAccount = "vera-us-oem-account12.mios.com",
                internalIp = "192.168.10.6",
                lastAliveReported = "2018-02-17 20:21:49",
                platform = "Sercomm G450"
            ),
            DeviceDataResponse(
                pkDevice = 50100070,
                macAddress = "94:4a:0c:25:64:05",
                pKDeviceType = 1,
                pKDeviceSubType = 2,
                firmware = "1.7.6255",
                serverDevice = "vera-us-oem-device11.mios.com",
                serverEvent = "vera-us-oem-event11.mios.com",
                serverAccount = "vera-us-oem-account11.mios.com",
                internalIp = "172.17.22.101",
                lastAliveReported = "2018-02-20 13:02:43",
                platform = "Sercomm G450"
            ),
            DeviceDataResponse(
                pkDevice = 50100072,
                macAddress = "94:4a:0c:25:64:0f",
                pKDeviceType = 1,
                pKDeviceSubType = 2,
                firmware = "1.7.6255",
                serverDevice = "vera-us-oem-device11.mios.com",
                serverEvent = "vera-us-oem-event12.mios.com",
                serverAccount = "vera-us-oem-account12.mios.com",
                internalIp = "192.168.100.102",
                lastAliveReported = "2018-02-20 10:41:48",
                platform = "Sercomm G450"
            ),
            DeviceDataResponse(
                pkDevice = 50100200,
                macAddress = "94:4a:0c:25:64:1e",
                pKDeviceType = 1,
                pKDeviceSubType  = 2,
                firmware = "1.7.6255",
                serverDevice = "vera-us-oem-device11.mios.com",
                serverEvent = "vera-us-oem-event11.mios.com",
                serverAccount = "vera-us-oem-account11.mios.com",
                internalIp = "172.17.15.10",
                lastAliveReported = "2018-02-12 10:17:41",
                platform = "Sercomm G450"
            )
        )
    )
}