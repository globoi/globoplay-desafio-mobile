package com.gmribas.globoplaydesafiomobile.core.di

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {

    single { androidContext() }
}