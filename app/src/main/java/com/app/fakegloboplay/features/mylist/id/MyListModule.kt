package com.app.fakegloboplay.features.mylist.id

import com.app.fakegloboplay.features.mylist.repository.MyListRepository
import com.app.fakegloboplay.features.mylist.screen.MyListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object MyListModule {
    val module = module {
        factory { MyListRepository(get()) }
        viewModel { MyListViewModel(get()) }
    }
}