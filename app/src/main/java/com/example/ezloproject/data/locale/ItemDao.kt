package com.example.ezloproject.data.locale

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ezloproject.data.model.locale.ItemEntity

@Dao
interface ItemDao {
    @Query("SELECT * FROM items ORDER BY pkDevice")
    fun getAllItems(): List<ItemEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(itemEntities: List<ItemEntity>)

    @Delete
    suspend fun delete(itemEntity: ItemEntity)

    @Query("DELETE FROM items")
    suspend fun deleteAll()
}