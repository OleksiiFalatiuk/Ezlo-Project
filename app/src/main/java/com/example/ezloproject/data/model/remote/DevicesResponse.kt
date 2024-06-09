package com.example.ezloproject.data.model.remote

import com.google.gson.annotations.SerializedName

data class DevicesResponse(
    @SerializedName("Devices" )
    val devices: List<DeviceDataResponse?>
)
