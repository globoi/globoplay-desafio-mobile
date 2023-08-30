package com.mazer.globoplayapp.presentation.di

import android.util.Log
import com.mazer.globoplayapp.BuildConfig
import com.mazer.globoplayapp.data.datasource.MovieDataSource
import com.mazer.globoplayapp.data.datasource.RemoteMovieDataSource
import com.mazer.globoplayapp.data.remote.ApiService
import com.mazer.globoplayapp.data.repos.MovieRepository
import com.mazer.globoplayapp.data.repos.MovieRepositoryImpl
import com.mazer.globoplayapp.domain.use_cases.GetMovieListUseCase
import com.mazer.globoplayapp.presentation.ui.main.MainActivityViewModel
import com.mazer.globoplayapp.presentation.ui.main.home.HomeFragmentViewModel
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val appModule = module {

    single<OkHttpClient> {
        OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request()
                    .newBuilder()
                    .addHeader("Authorization", BuildConfig.THEMOVIEDB_API_KEY)
                    .build()
                chain.proceed(request)
            }
            .build()
    }

   single<Retrofit> {
        Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
    }
    single<ApiService> {
        get<Retrofit>().create(ApiService::class.java)
    }

    factory<MovieDataSource> { RemoteMovieDataSource(get()) }
    factory<MovieRepository> { MovieRepositoryImpl(get()) }
    factory{ GetMovieListUseCase(get())}
    viewModel { HomeFragmentViewModel(get()) }
    viewModel { MainActivityViewModel(get()) }

}