package com.gmribas.globoplaydesafiomobile.core.presentation.di

import com.gmribas.globoplaydesafiomobile.feature.home.presentation.mapper.MovieUIMapper
import com.gmribas.globoplaydesafiomobile.feature.home.presentation.mapper.SoapOperaUIMapper
import com.gmribas.globoplaydesafiomobile.feature.home.presentation.mapper.TopRatedTvShowUIMapper
import org.koin.dsl.module

val uiMapperModule = module {

    single { MovieUIMapper() }

    single { SoapOperaUIMapper() }

    single { TopRatedTvShowUIMapper() }
}