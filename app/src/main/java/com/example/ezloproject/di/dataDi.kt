package com.example.ezloproject.di

import com.example.ezloproject.data.repository.ItemRemoteRepository
import org.koin.dsl.module

val dataDi = module {
    single { ItemRemoteRepository(get(), get()) }
}