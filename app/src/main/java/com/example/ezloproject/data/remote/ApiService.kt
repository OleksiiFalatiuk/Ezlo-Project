package com.example.ezloproject.data.remote

import com.example.ezloproject.data.model.remote.DevicesResponse
import retrofit2.http.GET

interface ApiService {
    @GET("items.test")
    suspend fun getItems(): DevicesResponse
}