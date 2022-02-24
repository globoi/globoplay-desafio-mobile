package com.globo.moviesapp.di.movie

import com.globo.moviesapp.ui.movie.fragment.MovieDatasheetFragment
import com.globo.moviesapp.ui.movie.fragment.MovieWatchTooFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MovieFragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeMovieDetailsFragment() : MovieDatasheetFragment

    @ContributesAndroidInjector
    abstract fun contributeMovieWatchTooFragment() : MovieWatchTooFragment
}