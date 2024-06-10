package com.example.ezloproject.data.remote

import com.example.ezloproject.Constants
import com.example.ezloproject.data.model.remote.DevicesResponse
import retrofit2.http.GET

interface ApiService {
    @GET(Constants.GET_ITEMS_REQUEST)
    suspend fun getItems(): DevicesResponse
}