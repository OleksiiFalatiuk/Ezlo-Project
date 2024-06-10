package com.example.ezloproject.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.ezloproject.data.model.local.ItemEntity

@Dao
interface ItemDao {
    @Query("SELECT * FROM items ORDER BY pkDevice")
    suspend fun getAllItems(): List<ItemEntity>

    @Query("SELECT * FROM items WHERE id = :itemId LIMIT 1")
    suspend fun getItemById(itemId: Int): ItemEntity?

    @Upsert
    suspend fun insertAll(itemEntities: List<ItemEntity>)

    @Query("DELETE FROM items WHERE id = :id")
    suspend fun delete(id: Int)

    @Query("UPDATE items SET title = :title WHERE id = :itemId")
    suspend fun updateItemTitle(title: String, itemId: Int)

    @Query("DELETE FROM items")
    suspend fun deleteAll()
}