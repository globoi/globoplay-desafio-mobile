package com.gmribas.globoplaydesafiomobile.core.presentation.di

import com.gmribas.globoplaydesafiomobile.feature.home.presentation.mapper.MovieUIMapper
import org.koin.dsl.module

val presentationModule = module {

    single { MovieUIMapper() }
}