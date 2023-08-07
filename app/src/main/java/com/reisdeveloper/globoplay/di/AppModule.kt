package com.reisdeveloper.globoplay.di

import com.reisdeveloper.globoplay.ui.features.home.HomeViewModel
import com.reisdeveloper.globoplay.ui.features.movie.moreDetails.MovieMoreDetailsViewModel
import com.reisdeveloper.globoplay.ui.features.movie.watchtoo.WatchViewModel
import com.reisdeveloper.globoplay.ui.features.movie.details.MovieDetailsViewModel
import com.reisdeveloper.globoplay.ui.features.movie.moreMovies.MoreMoviesListViewModel
import com.reisdeveloper.globoplay.ui.features.movie.player.PlayerMovieViewModel
import com.reisdeveloper.globoplay.ui.features.mylist.MyListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { MyListViewModel(get()) }
    viewModel { MovieDetailsViewModel(get()) }
    viewModel { WatchViewModel(get()) }
    viewModel { MovieMoreDetailsViewModel(get()) }
    viewModel { PlayerMovieViewModel(get()) }
    viewModel { HomeViewModel(get(), get()) }
    viewModel { MoreMoviesListViewModel(get()) }
}