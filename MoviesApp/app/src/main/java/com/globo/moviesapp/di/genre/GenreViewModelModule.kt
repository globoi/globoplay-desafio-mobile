package com.globo.moviesapp.di.genre

import androidx.lifecycle.ViewModel
import com.globo.moviesapp.di.ViewModelKey
import com.globo.moviesapp.ui.genre.viewmodel.GenreViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class GenreViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(GenreViewModel::class)
    abstract fun bindGenreViewModel(viewModel: GenreViewModel): ViewModel
}