package com.gmribas.globoplaydesafiomobile.core.domain.di

import com.gmribas.globoplaydesafiomobile.feature.home.domain.usecase.DiscoverMoviesUseCase
import com.gmribas.globoplaydesafiomobile.feature.home.domain.usecase.DiscoverSoapOperasUseCase
import org.koin.dsl.module

val useCaseModule = module {

    single { DiscoverMoviesUseCase(get()) }

    single { DiscoverSoapOperasUseCase(get()) }
}