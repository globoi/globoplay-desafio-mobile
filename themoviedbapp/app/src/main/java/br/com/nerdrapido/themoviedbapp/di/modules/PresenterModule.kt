package br.com.nerdrapido.themoviedbapp.di.modules

import br.com.nerdrapido.themoviedbapp.ui.home.HomePresenter
import br.com.nerdrapido.themoviedbapp.ui.home.HomePresenterImpl
import br.com.nerdrapido.themoviedbapp.ui.login.LoginPresenter
import br.com.nerdrapido.themoviedbapp.ui.login.LoginPresenterImpl
import br.com.nerdrapido.themoviedbapp.ui.moviedetail.MovieDetailPresenter
import br.com.nerdrapido.themoviedbapp.ui.moviedetail.MovieDetailPresenterImpl
import br.com.nerdrapido.themoviedbapp.ui.splash.SplashScreenPresenter
import br.com.nerdrapido.themoviedbapp.ui.splash.SplashScreenPresenterImpl
import org.koin.dsl.module

/**
 * Created By FELIPE GUSBERTI @ 10/03/2020
 */
class PresenterModule {
    companion object {
        fun getPresenterModule() = module {
            factory<SplashScreenPresenter> { SplashScreenPresenterImpl(get()) }
            factory<LoginPresenter> { LoginPresenterImpl(get(), get(), get(), get()) }
            factory<HomePresenter> { HomePresenterImpl(get(), get(), get()) }
            factory<MovieDetailPresenter> { MovieDetailPresenterImpl(get(), get()) }
        }
    }
}