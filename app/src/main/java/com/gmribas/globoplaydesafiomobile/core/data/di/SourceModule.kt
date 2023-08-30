package com.gmribas.globoplaydesafiomobile.core.data.di

import com.gmribas.globoplaydesafiomobile.feature.details.data.source.GetMovieDetailsSource
import com.gmribas.globoplaydesafiomobile.feature.details.data.source.GetSimilarMoviesSource
import com.gmribas.globoplaydesafiomobile.feature.details.data.source.GetSimilarTvShowsSource
import com.gmribas.globoplaydesafiomobile.feature.details.data.source.remote.GetMovieDetailsSourceRemoteImpl
import com.gmribas.globoplaydesafiomobile.feature.details.data.source.remote.GetSimilarMoviesRemoteImpl
import com.gmribas.globoplaydesafiomobile.feature.details.data.source.remote.GetSimilarTvShowsRemoteImpl
import com.gmribas.globoplaydesafiomobile.feature.home.data.source.DiscoverMoviesSource
import com.gmribas.globoplaydesafiomobile.feature.home.data.source.DiscoverSoapOperasSource
import com.gmribas.globoplaydesafiomobile.feature.home.data.source.GetTopRatedTvShowsSource
import com.gmribas.globoplaydesafiomobile.feature.home.data.source.remote.DiscoverMoviesSourceRemoteImpl
import com.gmribas.globoplaydesafiomobile.feature.home.data.source.remote.DiscoverSoapOperaSourceRemoteImpl
import com.gmribas.globoplaydesafiomobile.feature.home.data.source.remote.GetTopRatedTvShowsSourceRemoteImpl
import org.koin.dsl.module

val sourceModule = module {

    single<DiscoverMoviesSource> { DiscoverMoviesSourceRemoteImpl(get()) }

    single<DiscoverSoapOperasSource> { DiscoverSoapOperaSourceRemoteImpl(get()) }

    single<GetTopRatedTvShowsSource> { GetTopRatedTvShowsSourceRemoteImpl(get()) }

    single<GetMovieDetailsSource> { GetMovieDetailsSourceRemoteImpl(get()) }

    single<GetSimilarMoviesSource> { GetSimilarMoviesRemoteImpl(get()) }

    single<GetSimilarTvShowsSource> { GetSimilarTvShowsRemoteImpl(get()) }
}