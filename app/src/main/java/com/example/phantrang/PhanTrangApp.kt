package com.example.phantrang

import android.app.Application
import com.example.phantrang.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PhanTrangApp: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@PhanTrangApp)
            modules(arrayListOf(appModule))

        }
    }
}