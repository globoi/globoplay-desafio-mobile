package com.mazer.globoplayapp.presentation.di

import android.util.Log
import com.mazer.globoplayapp.BuildConfig
import com.mazer.globoplayapp.data.datasource.MovieDataSource
import com.mazer.globoplayapp.data.datasource.RemoteMovieDataSource
import com.mazer.globoplayapp.data.remote.ApiService
import com.mazer.globoplayapp.data.repos.MovieRepository
import com.mazer.globoplayapp.data.repos.MovieRepositoryImpl
import com.mazer.globoplayapp.domain.use_cases.GetMovieListUseCase
import com.mazer.globoplayapp.presentation.ui.details.MovieDetailsViewModel
import com.mazer.globoplayapp.presentation.ui.details.tabs.recommendation.RecommendationViewModel
import com.mazer.globoplayapp.presentation.ui.main.MainActivityViewModel
import com.mazer.globoplayapp.presentation.ui.main.home.HomeViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val appModule = module {

    single<OkHttpClient> {
        val loggingInterceptor = HttpLoggingInterceptor{ message -> Log.d("HttpLoggingInterceptor", message) }
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        OkHttpClient.Builder()
            .addNetworkInterceptor (loggingInterceptor)
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
            .baseUrl(BuildConfig.BASE_URL)
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
    viewModel { HomeViewModel(get()) }
    viewModel { MovieDetailsViewModel(get()) }
    viewModel { MainActivityViewModel() }
    viewModel { RecommendationViewModel(get()) }

}
