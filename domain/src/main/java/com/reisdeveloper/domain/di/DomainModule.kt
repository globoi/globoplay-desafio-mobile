package com.reisdeveloper.domain.di

import com.reisdeveloper.domain.usecases.FavoriteMovieUseCase
import com.reisdeveloper.domain.usecases.GetFavoriteMoviesUseCase
import com.reisdeveloper.domain.usecases.GetListUseCase
import com.reisdeveloper.domain.usecases.GetMovieDetailsUseCase
import com.reisdeveloper.domain.usecases.GetMovieListPagingUseCase
import com.reisdeveloper.domain.usecases.GetMovieListUseCase
import com.reisdeveloper.domain.usecases.GetMovieVideosUseCase
import com.reisdeveloper.domain.usecases.GetNowPlayingMoviesUseCase
import com.reisdeveloper.domain.usecases.GetPopularMoviesUseCase
import com.reisdeveloper.domain.usecases.GetSimilarMoviesUseCase
import com.reisdeveloper.domain.usecases.GetTopRatedMoviesUseCase
import com.reisdeveloper.domain.usecases.GetUpcomingMoviesUseCase
import com.reisdeveloper.domain.usecases.SearchMoviesUseCase
import org.koin.dsl.module

val domainModule = module {
    single { GetListUseCase(get()) }
    single { GetFavoriteMoviesUseCase(get()) }
    single { GetSimilarMoviesUseCase(get()) }
    single { GetMovieDetailsUseCase(get()) }
    single { FavoriteMovieUseCase(get()) }
    single { GetMovieVideosUseCase(get()) }
    single { GetPopularMoviesUseCase(get()) }
    single { GetNowPlayingMoviesUseCase(get()) }
    single { GetTopRatedMoviesUseCase(get()) }
    single { GetUpcomingMoviesUseCase(get()) }
    single { SearchMoviesUseCase(get()) }
    single { GetMovieListUseCase(get()) }
    single { GetMovieListPagingUseCase(get()) }
}