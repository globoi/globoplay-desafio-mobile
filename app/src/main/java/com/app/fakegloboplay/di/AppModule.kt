package com.app.fakegloboplay.di

import com.app.fakegloboplay.features.detailmovie.id.DetailMovieModule
import com.app.fakegloboplay.network.APIService
import com.app.fakegloboplay.network.AuthInterceptor
import com.app.fakegloboplay.features.home.id.HomeMovieModule
import com.app.fakegloboplay.features.mylist.id.MyListModule
import org.koin.dsl.module

object AppModule {
    private val networkModule = module {
        factory { AuthInterceptor() }
        factory { APIService.provideOkHttpClient(get()) }
        factory { APIService.provideService(get()) }
        single { APIService.provideRetrofit(get()) }
    }

    val modules = listOf(
        networkModule,
        HomeMovieModule.module,
        DetailMovieModule.module,
        MyListModule.module
    )
}