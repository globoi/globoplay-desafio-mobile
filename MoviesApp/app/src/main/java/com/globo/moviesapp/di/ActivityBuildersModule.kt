package com.globo.moviesapp.di

import com.globo.moviesapp.di.account.AccountViewModelModule
import com.globo.moviesapp.di.genre.GenreViewModelModule
import com.globo.moviesapp.di.movie.MovieFragmentBuildersModule
import com.globo.moviesapp.di.movie.MovieViewModelModule
import com.globo.moviesapp.di.panel.PanelFragmentBuildersModule
import com.globo.moviesapp.ui.movie.view.MovieDetailsActivity
import com.globo.moviesapp.ui.panel.activity.PanelActivity
import com.globo.moviesapp.ui.splash.activity.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {
    @ContributesAndroidInjector(
        modules = [
            AccountViewModelModule::class
        ]
    )
    abstract fun contributeSplashActivity() : SplashActivity

    @ContributesAndroidInjector(
        modules = [
            PanelFragmentBuildersModule::class,
            GenreViewModelModule::class,
            MovieViewModelModule::class
        ]
    )
    abstract fun contributePanelActivity() : PanelActivity

    @ContributesAndroidInjector(
        modules = [
            MovieFragmentBuildersModule::class,
            MovieViewModelModule::class
        ]
    )
    abstract fun contributeMovieDetailsActivity() : MovieDetailsActivity
}