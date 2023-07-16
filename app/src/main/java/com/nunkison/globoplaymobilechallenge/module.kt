package com.nunkison.globoplaymobilechallenge

import com.nunkison.globoplaymobilechallenge.project.api.TmdbService
import com.nunkison.globoplaymobilechallenge.project.structure.MoviesRepository
import com.nunkison.globoplaymobilechallenge.repo.MoviesRepositoryImpl
import com.nunkison.globoplaymobilechallenge.ui.movie_detail.MovieDetailViewModelImpl
import com.nunkison.globoplaymobilechallenge.ui.movies.MoviesViewModelImpl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val androidModule = module {

    single<TmdbService> {
        Retrofit.Builder()
            .baseUrl(androidContext().getString(R.string.base_url))
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder().addInterceptor(
                    Interceptor { chain ->
                        val newRequest: Request = chain.request().newBuilder()
                            .addHeader("Authorization", "Bearer ${androidContext().getString(R.string.api_key)}")
                            .build()
                        chain.proceed(newRequest)
                    }
                ).build()
            ).build().create(TmdbService::class.java)
    }

    factory<MoviesRepository> {
        MoviesRepositoryImpl(
            service = get()
        )
    }

    viewModel {
        MoviesViewModelImpl(
            repo = get()
        )
    }

    viewModel {params ->
        MovieDetailViewModelImpl(
            repo = get(),
            id = params.get()
        )
    }
}