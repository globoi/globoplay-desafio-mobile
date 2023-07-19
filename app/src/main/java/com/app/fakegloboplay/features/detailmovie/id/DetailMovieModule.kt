package com.app.fakegloboplay.features.detailmovie.id

import com.app.fakegloboplay.features.detailmovie.repository.DetailMoveRepository
import com.app.fakegloboplay.features.detailmovie.screen.DetailDatasheetViewModel
import com.app.fakegloboplay.features.detailmovie.screen.DetailWatchTooViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object DetailMovieModule {

    val module = module {

        factory { DetailMoveRepository(get()) }

        viewModel { DetailWatchTooViewModel(get()) }
        viewModel { DetailDatasheetViewModel(get()) }
    }
}