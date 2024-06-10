package com.example.ezloproject.data.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.ezloproject.Constants
import java.util.Random

@Entity(tableName = Constants.ITEMS_TABLE)
data class ItemEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val pkDevice: Int,
    val macAddress: String,
    val firmware: String,
    val platform: String,
    val title: String
)
