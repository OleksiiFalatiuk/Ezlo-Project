package com.example.ezloproject.data.model.locale

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items")
data class ItemEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val pkDevice: Int,
    val macAddress: String,
    val firmware: String,
    val platform: String,
    val title: String
)
