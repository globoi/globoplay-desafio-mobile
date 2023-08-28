package com.gmribas.globoplaydesafiomobile.core.data.di

import com.gmribas.globoplaydesafiomobile.feature.details.data.repository.GetMovieDetailsRepositoryImpl
import com.gmribas.globoplaydesafiomobile.feature.details.domain.repository.GetMovieDetailsRepository
import com.gmribas.globoplaydesafiomobile.feature.home.data.repository.DiscoverMoviesRepositoryImpl
import com.gmribas.globoplaydesafiomobile.feature.home.data.repository.DiscoverSoapOperasRepositoryImpl
import com.gmribas.globoplaydesafiomobile.feature.home.data.repository.GetTopRatedTvShowsRepositoryImpl
import com.gmribas.globoplaydesafiomobile.feature.home.domain.repository.DiscoverMoviesRepository
import com.gmribas.globoplaydesafiomobile.feature.home.domain.repository.DiscoverSoapOperasRepository
import com.gmribas.globoplaydesafiomobile.feature.home.domain.repository.GetTopRatedTvShowsRepository
import org.koin.dsl.module

val repositoryModule = module {

    single<DiscoverMoviesRepository> { DiscoverMoviesRepositoryImpl(get()) }

    single<DiscoverSoapOperasRepository> { DiscoverSoapOperasRepositoryImpl(get()) }

    single<GetTopRatedTvShowsRepository> { GetTopRatedTvShowsRepositoryImpl(get()) }

    single<GetMovieDetailsRepository> { GetMovieDetailsRepositoryImpl(get()) }
}