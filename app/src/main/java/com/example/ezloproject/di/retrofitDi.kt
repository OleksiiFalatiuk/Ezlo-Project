package com.example.ezloproject.di

import com.example.ezloproject.data.remote.RetrofitInstance
import org.koin.dsl.module

val retrofitDi = module {
    single { RetrofitInstance().api }
}