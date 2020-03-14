package br.com.nerdrapido.themoviedbapp.ui.abstracts.navigation

import br.com.nerdrapido.themoviedbapp.ui.abstracts.Presenter

/**
 * Created By FELIPE GUSBERTI @ 14/03/2020
 */
interface NavigationPresenter<V: NavigationView>: Presenter<V> {

    /**
     * This function is called when the user decides to logout.
     */
    fun logoutWasCalled()


}