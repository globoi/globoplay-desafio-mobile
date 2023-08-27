package com.gmribas.globoplaydesafiomobile.core.data.di

import com.gmribas.globoplaydesafiomobile.feature.home.data.source.DiscoverMoviesSource
import com.gmribas.globoplaydesafiomobile.feature.home.data.source.remote.DiscoverMoviesSourceRemoteImpl
import org.koin.dsl.module

val sourceModule = module {
    single<DiscoverMoviesSource> { DiscoverMoviesSourceRemoteImpl(get()) }
}