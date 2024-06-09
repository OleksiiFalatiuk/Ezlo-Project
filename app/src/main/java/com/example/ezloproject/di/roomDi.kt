package com.example.ezloproject.di

import com.example.ezloproject.data.locale.AppDatabase
import org.koin.dsl.module

val roomDi = module {
    single {
        AppDatabase.getDatabase(get()).itemDao()
    }
}