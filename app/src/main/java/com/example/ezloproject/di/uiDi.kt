package com.example.ezloproject.di

import com.example.ezloproject.ui.viewmodel.BaseViewModel
import com.example.ezloproject.ui.viewmodel.ItemDetailsViewModel
import com.example.ezloproject.ui.viewmodel.MainViewModel
import com.example.ezloproject.ui.viewmodel.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiDi = module {

    viewModel{
        BaseViewModel()
    }

    viewModel{
        SplashViewModel(
            repository = get()
        )
    }

    viewModel{
        MainViewModel(
            repository = get()
        )
    }

    viewModel{
        ItemDetailsViewModel(
            repository = get()
        )
    }
}