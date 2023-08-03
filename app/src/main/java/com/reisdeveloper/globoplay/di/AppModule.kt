package com.reisdeveloper.globoplay.di

import com.reisdeveloper.globoplay.ui.features.mylist.MyListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { MyListViewModel(get()) }
}