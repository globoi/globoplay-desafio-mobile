package com.nroncari.movieplay.di

import com.nroncari.movieplay.data.datasource.*
import com.nroncari.movieplay.data.repository.MovieRepositoryImpl
import com.nroncari.movieplay.data.retrofit.HttpClient
import com.nroncari.movieplay.data.retrofit.RetrofitClient
import com.nroncari.movieplay.data.service.MovieService
import com.nroncari.movieplay.domain.repository.MovieRepository
import com.nroncari.movieplay.domain.usecase.*
import com.nroncari.movieplay.presentation.viewmodel.HomeViewModel
import com.nroncari.movieplay.presentation.viewmodel.MovieDetailViewModel
import com.nroncari.movieplay.presentation.viewmodel.SearchViewModel
import com.nroncari.movieplay.presentation.viewmodel.StateAppComponentsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dataModules = module {
    factory<MovieDataSource> { MovieDataSourceImpl(service = get()) }
    factory<MoviePagingSourceByGenre> { MoviePagingSourceByGenre(service = get()) }
    factory<MoviePagingSourceRecommendations> { MoviePagingSourceRecommendations(service = get()) }
    factory<MoviePagingSourceByKeyword> { MoviePagingSourceByKeyword(service = get()) }
    factory<MovieRepository> {
        MovieRepositoryImpl(
            dataSource = get(),
            moviePagingSourceByGenre = get(),
            moviePagingSourceRecommendations = get(),
            moviePagingSourceByKeyword = get()
        )
    }
}

val domainModules = module {
    factory { GetActionMoviesUseCase(repository = get()) }
    factory { GetMovieDetailUseCase(repository = get()) }
    factory { GetHorrorMoviesUseCase(repository = get()) }
    factory { GetAnimationMoviesUseCase(repository = get()) }
    factory { GetComedyMoviesUseCase(repository = get()) }
    factory { GetDramaMoviesUseCase(repository = get()) }
    factory { GetMovieDataVideoUseCase(repository = get()) }
    factory { GetMovieRecommendationsUseCase(repository = get()) }
    factory { GetMoviesByKeywordUseCase(repository = get()) }
}

val networkModules = module {
    single { RetrofitClient(application = androidContext()).newInstance() }
    single { HttpClient(get()) }
    factory { get<HttpClient>().create(MovieService::class.java) }
}

val presentationModules = module {
    viewModel {
        HomeViewModel(
            getActionMovies = get(),
            getAnimationMovies = get(),
            getComedyMovies = get(),
            getDramaMovies = get(),
            getHorrorMovies = get()
        )
    }
    viewModel {
        MovieDetailViewModel(
            getMovieDetailUseCase = get(),
            getMovieDataVideoUseCase = get(),
            recommendationsUseCase = get(),
        )
    }
    viewModel { SearchViewModel(useCase = get()) }
    viewModel { StateAppComponentsViewModel() }
}