package com.example.ezloproject.data.locale

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.example.ezloproject.data.model.locale.ItemEntity

@Dao
interface ItemDao {
    @Query("SELECT * FROM items ORDER BY pkDevice")
    fun getAllItems(): List<ItemEntity>

    @Query("SELECT * FROM items WHERE id = :itemId LIMIT 1")
    fun getItemById(itemId: Int): ItemEntity?

    @Upsert
    suspend fun insertAll(itemEntities: List<ItemEntity>)

    @Query("DELETE FROM items WHERE id = :id")
    suspend fun delete(id: Int)

    @Query("UPDATE items SET title = :title WHERE id = :itemId")
    suspend fun updateItemTitle( title: String ,itemId: Int)
}