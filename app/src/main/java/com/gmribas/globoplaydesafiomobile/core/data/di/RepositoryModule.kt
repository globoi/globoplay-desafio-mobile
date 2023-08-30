package com.gmribas.globoplaydesafiomobile.core.data.di

import com.gmribas.globoplaydesafiomobile.feature.details.data.repository.GetMovieDetailsRepositoryImpl
import com.gmribas.globoplaydesafiomobile.feature.details.domain.repository.GetMovieDetailsRepository
import com.gmribas.globoplaydesafiomobile.feature.details.domain.repository.GetSimilarMoviesRepository
import com.gmribas.globoplaydesafiomobile.feature.home.data.repository.DiscoverMoviesRepositoryImpl
import com.gmribas.globoplaydesafiomobile.feature.home.data.repository.DiscoverTvShowsRepositoryImpl
import com.gmribas.globoplaydesafiomobile.feature.details.data.repository.GetSimilarMoviesRepositoryImpl
import com.gmribas.globoplaydesafiomobile.feature.details.data.repository.GetSimilarTvShowsRepositoryImpl
import com.gmribas.globoplaydesafiomobile.feature.details.data.repository.GetTvShowDetailsRepositoryImpl
import com.gmribas.globoplaydesafiomobile.feature.details.domain.repository.GetSimilarTvShowsRepository
import com.gmribas.globoplaydesafiomobile.feature.details.domain.repository.GetTvShowDetailsRepository
import com.gmribas.globoplaydesafiomobile.feature.home.data.repository.GetTopRatedTvShowsRepositoryImpl
import com.gmribas.globoplaydesafiomobile.feature.home.domain.repository.DiscoverMoviesRepository
import com.gmribas.globoplaydesafiomobile.feature.home.domain.repository.DiscoverTvShowsRepository
import com.gmribas.globoplaydesafiomobile.feature.home.domain.repository.GetTopRatedTvShowsRepository
import org.koin.dsl.module

val repositoryModule = module {

    single<DiscoverMoviesRepository> { DiscoverMoviesRepositoryImpl(get()) }

    single<DiscoverTvShowsRepository> { DiscoverTvShowsRepositoryImpl(get()) }

    single<GetTopRatedTvShowsRepository> { GetTopRatedTvShowsRepositoryImpl(get()) }

    single<GetMovieDetailsRepository> { GetMovieDetailsRepositoryImpl(get()) }

    single<GetSimilarMoviesRepository> { GetSimilarMoviesRepositoryImpl(get()) }

    single<GetSimilarTvShowsRepository> { GetSimilarTvShowsRepositoryImpl(get()) }

    single<GetTvShowDetailsRepository> { GetTvShowDetailsRepositoryImpl(get()) }
}