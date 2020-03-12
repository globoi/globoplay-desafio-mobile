package br.com.nerdrapido.themoviedbapp.ui.home

import br.com.nerdrapido.themoviedbapp.ui.abstracts.Presenter

/**
 * Created By FELIPE GUSBERTI @ 10/03/2020
 */
interface HomePresenter : Presenter<HomeView> {

    /**
     * This function is called when the user decides to logout.
     */
    fun logoutWasCalled()
}