package com.globo.moviesapp.di.panel

import com.globo.moviesapp.ui.genre.fragment.GenreMoviesFragment
import com.globo.moviesapp.ui.movie.fragment.MovieFavoriteFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class PanelFragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeGenreMoviesFragment() : GenreMoviesFragment

    @ContributesAndroidInjector
    abstract fun contributeMovieFavoriteFragment() : MovieFavoriteFragment
}