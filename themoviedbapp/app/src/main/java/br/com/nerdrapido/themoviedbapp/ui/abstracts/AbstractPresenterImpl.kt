package br.com.nerdrapido.themoviedbapp.ui.abstracts

import br.com.nerdrapido.themoviedbapp.domain.usecase.GetLogInStateUseCase
import timber.log.Timber

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
    protected open fun needsToBeLoggedIn(): Boolean {
        return true
    }

    /**
     * Returns a [Boolean] value: if true the user needs to be routed to the login routine, if false
     * the user can continue.
     */
    protected fun needToGoBackToLoginCheck() {
        if (needsToBeLoggedIn() && !isLoggedIn()) {
            view.goBackToLogin()
        }
    }

    override fun viewIsInvoked() {
        Timber.d("%s view Is Invoked", view.javaClass.simpleName)
        needToGoBackToLoginCheck()
    }

    override fun viewIsAboutToBeShown() {
        Timber.d("%s Is About To BeShown", view.javaClass.simpleName)
        needToGoBackToLoginCheck()
    }

    override fun viewIsClosed() {
        Timber.d("%s is closed", view.javaClass.simpleName)
    }


}