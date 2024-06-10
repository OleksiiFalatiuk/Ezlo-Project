package com.example.ezloproject.di

import com.example.ezloproject.ui.viewmodel.ItemDetailsViewModel
import com.example.ezloproject.ui.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiDi = module {
    viewModel {
        MainViewModel(
            repository = get()
        )
    }

    viewModel {
        ItemDetailsViewModel(
            repository = get()
        )
    }
}