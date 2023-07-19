package com.app.fakegloboplay.features.home.id

import com.app.fakegloboplay.features.home.repository.HomeRepository
import com.app.fakegloboplay.features.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object HomeMovieModule {
    val module = module {
        factory { HomeRepository(get()) }
        viewModel { HomeViewModel(get()) }
    }
}