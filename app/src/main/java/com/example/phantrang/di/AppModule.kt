package com.example.phantrang.di

import com.example.phantrang.main.MainActivityViewModel
import com.example.phantrang.data.repo.NumberRepository
import com.example.phantrang.data.api.Firestore
import com.example.phantrang.data.db.NumberDao
import com.example.phantrang.data.db.NumberDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<NumberDao> { NumberDatabase.getInstance(androidApplication()).numberDao() }
    single { NumberRepository(get(), get()) }
    single { Firestore() }
    viewModel { MainActivityViewModel(get()) }

}