package com.gmribas.globoplaydesafiomobile.core.presentation.di

import com.gmribas.globoplaydesafiomobile.feature.details.presentation.DetailsScreenViewModel
import com.gmribas.globoplaydesafiomobile.feature.home.presentation.HomeScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::HomeScreenViewModel)

    viewModelOf(::DetailsScreenViewModel)
}