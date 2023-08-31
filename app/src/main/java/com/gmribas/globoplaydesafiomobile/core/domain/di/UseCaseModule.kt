package com.gmribas.globoplaydesafiomobile.core.domain.di

import com.gmribas.globoplaydesafiomobile.feature.details.domain.usecase.FindMediaByIdUseCase
import com.gmribas.globoplaydesafiomobile.feature.details.domain.usecase.GetMovieDetailsUseCase
import com.gmribas.globoplaydesafiomobile.feature.details.domain.usecase.GetSimilarMoviesUseCase
import com.gmribas.globoplaydesafiomobile.feature.details.domain.usecase.GetSimilarTvShowsUseCase
import com.gmribas.globoplaydesafiomobile.feature.details.domain.usecase.GetTvShowDetailsUseCase
import com.gmribas.globoplaydesafiomobile.feature.home.domain.usecase.DiscoverMoviesUseCase
import com.gmribas.globoplaydesafiomobile.feature.home.domain.usecase.DiscoverTvShowsUseCase
import com.gmribas.globoplaydesafiomobile.feature.home.domain.usecase.GetTopRatedTvShowsUseCase
import com.gmribas.globoplaydesafiomobile.feature.mylist.domain.usecase.GetAllSavedMediaUseCase
import com.gmribas.globoplaydesafiomobile.feature.details.domain.usecase.RemoveMediaUseCase
import com.gmribas.globoplaydesafiomobile.feature.details.domain.usecase.SaveMovieUseCase
import com.gmribas.globoplaydesafiomobile.feature.details.domain.usecase.SaveTvShowUseCase
import org.koin.dsl.module

val useCaseModule = module {

    single { DiscoverMoviesUseCase(get()) }

    single { DiscoverTvShowsUseCase(get()) }

    single { GetTopRatedTvShowsUseCase(get()) }

    single { GetMovieDetailsUseCase(get()) }

    single { GetSimilarMoviesUseCase(get()) }

    single { GetSimilarTvShowsUseCase(get()) }

    single { GetTvShowDetailsUseCase(get()) }

    single { GetAllSavedMediaUseCase(get()) }

    single { RemoveMediaUseCase(get()) }

    single { SaveTvShowUseCase(get()) }

    single { SaveMovieUseCase(get()) }

    single { FindMediaByIdUseCase(get()) }
}