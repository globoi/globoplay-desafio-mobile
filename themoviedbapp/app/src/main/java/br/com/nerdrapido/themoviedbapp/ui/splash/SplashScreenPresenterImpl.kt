package br.com.nerdrapido.themoviedbapp.ui.splash

import br.com.nerdrapido.themoviedbapp.domain.usecase.GetLogInStateUseCase
import br.com.nerdrapido.themoviedbapp.domain.usecase.LogoutUseCase
import br.com.nerdrapido.themoviedbapp.ui.abstracts.AbstractPresenterImpl

/**
 * Created By FELIPE GUSBERTI @ 08/03/2020
 */
class SplashScreenPresenterImpl(
    logoutUseCase: LogoutUseCase,
    getLogInStateUseCase: GetLogInStateUseCase
) : AbstractPresenterImpl<SplashScreenView>(
    logoutUseCase,
    getLogInStateUseCase
), SplashScreenPresenter {

    override fun needsToBeLoggedIn(): Boolean {
        return false
    }

    override fun endOfSplashScreen() {
        if (isLoggedIn()) {
            view.goHome()
        } else {
            view.goLogin()
        }
    }

}