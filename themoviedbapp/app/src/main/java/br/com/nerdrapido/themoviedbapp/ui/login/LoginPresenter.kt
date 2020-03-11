package br.com.nerdrapido.themoviedbapp.ui.login

import br.com.nerdrapido.themoviedbapp.ui.abstracts.Presenter

/**
 * Created By FELIPE GUSBERTI @ 08/03/2020
 */
interface LoginPresenter : Presenter<LoginView> {

    /**
     * Called when the user start a login attempt.
     */
    fun loginWasCalled()

    /**
     *
     */
    fun loginAttemptHasResponse(response: Any)

    fun loginSuccess()

    /**
     * Is Called when the splash screen should be dismissed and the app move on.
     */
    fun endOfSplashScreen()
}