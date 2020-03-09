package br.com.nerdrapido.themoviedbapp.ui.abstracts

import br.com.nerdrapido.themoviedbapp.domain.usecase.GetLogInStateUseCase

/**
 * Created By FELIPE GUSBERTI @ 08/03/2020
 */

abstract class AbstractPresenterImpl<V : View>(
    private val getLogInStateUseCase: GetLogInStateUseCase
) : Presenter<V> {

    protected lateinit var view: V

    override fun initializeView(view: V) {
        this.view = view
    }

    /**
     * Returns a [Boolean] value: if true the user is logged in, if false teh user is not.
     */
    protected fun isLoggedIn(): Boolean {
        return getLogInStateUseCase.isLoggedIn()
    }

    /**
     * Returns a [Boolean] value: if true the user needs to be logged in order to access this app
     * section, if false teh user needs not.
     */
    private fun needsToBeLoggedIn(): Boolean {
        return true
    }

    /**
     * Returns a [Boolean] value: if true the user needs to be routed to the login routine, if false
     * the user can continue.
     */
    private fun needToGoBackToLoginCheck() {
        if (!needsToBeLoggedIn() && isLoggedIn()) {
            view.goBackToLogin()
        }
    }

    override fun viewIsInvoked() {
        needToGoBackToLoginCheck()
    }

    override fun viewIsAboutToBeShown() {
        needToGoBackToLoginCheck()
    }

    override fun viewIsClosed() {
        TODO("Not yet implemented")
    }


}