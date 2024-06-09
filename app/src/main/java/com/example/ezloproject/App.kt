package com.example.ezloproject

import android.app.Application
import com.example.ezloproject.di.dataDi
import com.example.ezloproject.di.retrofitDi
import com.example.ezloproject.di.roomDi
import com.example.ezloproject.di.uiDi
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        GlobalContext.startKoin {
            androidContext(this@App)
            modules(listOf(uiDi, roomDi, retrofitDi, dataDi))
        }
    }
}