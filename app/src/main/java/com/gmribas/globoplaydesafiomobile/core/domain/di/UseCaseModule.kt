package com.gmribas.globoplaydesafiomobile.core.domain.di

import com.gmribas.globoplaydesafiomobile.feature.details.domain.usecase.GetMovieDetailsUseCase
import com.gmribas.globoplaydesafiomobile.feature.details.domain.usecase.GetSimilarMoviesUseCase
import com.gmribas.globoplaydesafiomobile.feature.home.domain.usecase.DiscoverMoviesUseCase
import com.gmribas.globoplaydesafiomobile.feature.home.domain.usecase.DiscoverSoapOperasUseCase
import com.gmribas.globoplaydesafiomobile.feature.home.domain.usecase.GetTopRatedTvShowsUseCase
import org.koin.dsl.module

val useCaseModule = module {

    single { DiscoverMoviesUseCase(get()) }

    single { DiscoverSoapOperasUseCase(get()) }

    single { GetTopRatedTvShowsUseCase(get()) }

    single { GetMovieDetailsUseCase(get()) }

    single { GetSimilarMoviesUseCase(get()) }
}