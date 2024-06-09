package com.example.ezloproject.data.model.locale

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "items")
data class ItemEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val pkDevice: Int,
    val platform: String,
    val title: String = "Home number $id"
)
