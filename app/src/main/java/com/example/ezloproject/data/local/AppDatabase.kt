package com.example.ezloproject.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ezloproject.Constants
import com.example.ezloproject.data.model.local.ItemEntity

@Database(entities = [ItemEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun itemDao(): ItemDao

    companion object {
        fun getDatabase(context: Context): AppDatabase = Room.databaseBuilder(
            context, AppDatabase::class.java, Constants.DB_NAME
        ).build()
    }
}