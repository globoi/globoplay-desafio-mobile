package com.nroncari.movieplay.di

import androidx.room.Room
import com.nroncari.movieplay.data.localdatasource.ConnectionDatabase
import com.nroncari.movieplay.data.localdatasource.MovieLocalDataSource
import com.nroncari.movieplay.data.localdatasource.MovieLocalDataSourceImpl
import com.nroncari.movieplay.data.localdatasource.dao.MovieDetailDAO
import com.nroncari.movieplay.data.remotedatasource.*
import com.nroncari.movieplay.data.remotedatasource.retrofit.HttpClient
import com.nroncari.movieplay.data.remotedatasource.retrofit.RetrofitClient
import com.nroncari.movieplay.data.remotedatasource.service.MovieRemoteService
import com.nroncari.movieplay.data.repository.MovieLocalRepositoryImpl
import com.nroncari.movieplay.data.repository.MovieRemoteRepositoryImpl
import com.nroncari.movieplay.domain.repository.MovieLocalRepository
import com.nroncari.movieplay.domain.repository.MovieRemoteRepository
import com.nroncari.movieplay.domain.usecase.*
import com.nroncari.movieplay.presentation.viewmodel.*
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private const val NAME_DATABASE = "movieplay.db"

val databaseModule = module {
    single<ConnectionDatabase> {
        Room.databaseBuilder(
            get(),
            ConnectionDatabase::class.java,
            NAME_DATABASE
        ).build()
    }
}

val daoModule = module {
    single<MovieDetailDAO> { get<ConnectionDatabase>().movieDao() }
}

val dataModules = module {
    factory<MovieLocalDataSource> { MovieLocalDataSourceImpl(dao = get()) }
    factory<MovieRemoteDataSource> { MovieRemoteDataSourceImpl(service = get()) }
    factory<MoviePagingSourceByGenre> { MoviePagingSourceByGenre(service = get()) }
    factory<MoviePagingSourceRecommendations> { MoviePagingSourceRecommendations(service = get()) }
    factory<MoviePagingSourceByKeyword> { MoviePagingSourceByKeyword(service = get()) }
    factory<MovieRemoteRepository> {
        MovieRemoteRepositoryImpl(
            remoteDataSource = get(),
            moviePagingSourceByGenre = get(),
            moviePagingSourceRecommendations = get(),
            moviePagingSourceByKeyword = get(),
        )
    }
    factory<MovieLocalRepository> {
        MovieLocalRepositoryImpl(dataSource = get())
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
    factory { GetMovieDatabaseById(repository = get()) }
    factory { InsertMovieDatabaseUseCase(repository = get()) }
    factory { ListAllMovieDatabaseUseCase(repository = get()) }
    factory { RemoveMovieDatabaseUseCase(repository = get()) }
}

val networkModules = module {
    single { RetrofitClient(application = androidContext()).newInstance() }
    single { HttpClient(get()) }
    factory { get<HttpClient>().create(MovieRemoteService::class.java) }
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
            insertMovieDBUseCase = get(),
            removeMovieDBUseCase = get(),
            getMovieDatabaseById = get()
        )
    }
    viewModel { SearchViewModel(useCase = get()) }
    viewModel { StateAppComponentsViewModel() }
    viewModel { MyListViewModel(listAllUseCase = get()) }
}