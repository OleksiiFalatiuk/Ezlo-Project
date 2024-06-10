package com.example.ezloproject.di

import com.example.ezloproject.data.repository.ItemRepository
import com.example.ezloproject.data.repository.ItemRepositoryImpl
import org.koin.dsl.module

val dataDi = module {
    single<ItemRepository> { ItemRepositoryImpl(get(), get()) }
}