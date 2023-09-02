package com.gmribas.globoplaydesafiomobile.core.data.database

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {

    single { AppDatabase.getInstance(androidContext()) }

    single { AppDatabase.getInstance(androidContext()).mediaDAO() }
}