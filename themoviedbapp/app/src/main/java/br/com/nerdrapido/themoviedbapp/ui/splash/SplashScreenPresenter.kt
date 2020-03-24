package br.com.nerdrapido.themoviedbapp.ui.splash

import br.com.nerdrapido.themoviedbapp.ui.abstracts.Presenter

/**
 * Created By FELIPE GUSBERTI @ 08/03/2020
 */
interface SplashScreenPresenter: Presenter<SplashScreenView> {

    /**
     * Is Called when the splash screen should be dismissed and the app move on.
     */
    fun endOfSplashScreen()
}