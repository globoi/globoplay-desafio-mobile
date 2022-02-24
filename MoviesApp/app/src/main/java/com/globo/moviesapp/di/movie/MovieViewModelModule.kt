package com.globo.moviesapp.di.movie

import androidx.lifecycle.ViewModel
import com.globo.moviesapp.di.ViewModelKey
import com.globo.moviesapp.ui.movie.viewmodel.MovieViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MovieViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MovieViewModel::class)
    abstract fun bindMovieViewModel(viewModel: MovieViewModel): ViewModel
}