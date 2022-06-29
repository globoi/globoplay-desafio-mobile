package com.nroncari.movieplay.di

import com.nroncari.movieplay.data.datasource.MovieDataSource
import com.nroncari.movieplay.data.datasource.MovieDataSourceImpl
import com.nroncari.movieplay.data.datasource.MoviePagingSource
import com.nroncari.movieplay.data.repository.MovieRepositoryImpl
import com.nroncari.movieplay.data.retrofit.HttpClient
import com.nroncari.movieplay.data.retrofit.RetrofitClient
import com.nroncari.movieplay.data.service.MovieService
import com.nroncari.movieplay.domain.repository.MovieRepository
import com.nroncari.movieplay.domain.usecase.GetMovieDetailUseCase
import com.nroncari.movieplay.domain.usecase.GetMoviesByGenreUseCase
import com.nroncari.movieplay.presentation.viewmodel.HomeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dataModules = module {
    factory<MovieDataSource> { MovieDataSourceImpl(service = get()) }
    factory<MoviePagingSource> { MoviePagingSource(service = get()) }
    factory<MovieRepository> { MovieRepositoryImpl(dataSource = get(), moviePagingSource = get()) }
}

val domainModules = module {
    factory { GetMoviesByGenreUseCase(repository = get()) }
    factory { GetMovieDetailUseCase(repository = get()) }
}

val networkModules = module {
    single { RetrofitClient(application = androidContext()).newInstance() }
    single { HttpClient(get()) }
    factory { get<HttpClient>().create(MovieService::class.java) }
}

val presentationModules = module {
    viewModel { HomeViewModel(getMoviesByGenre = get()) }
}