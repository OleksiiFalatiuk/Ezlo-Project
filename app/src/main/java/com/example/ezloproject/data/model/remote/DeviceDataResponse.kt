package com.example.ezloproject.data.model.remote

import com.google.gson.annotations.SerializedName

data class DeviceDataResponse(
    @SerializedName("PK_Device")
    val pkDevice: Int?,
    @SerializedName("MacAddress")
    val macAddress: String?,
    @SerializedName("PK_DeviceType")
    val pKDeviceType: Int?,
    @SerializedName("PK_DeviceSubType")
    val pKDeviceSubType: Int?,
    @SerializedName("Firmware")
    val firmware: String?,
    @SerializedName("Server_Device")
    val serverDevice: String?,
    @SerializedName("Server_Event")
    val serverEvent: String?,
    @SerializedName("Server_Account")
    val serverAccount: String?,
    @SerializedName("InternalIP")
    val internalIp: String?,
    @SerializedName("LastAliveReported")
    val lastAliveReported: String?,
    @SerializedName("Platform")
    val platform: String?
)
