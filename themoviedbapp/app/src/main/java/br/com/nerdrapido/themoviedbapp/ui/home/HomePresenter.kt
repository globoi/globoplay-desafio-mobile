package br.com.nerdrapido.themoviedbapp.ui.home

import br.com.nerdrapido.themoviedbapp.data.model.MovieListResultObject
import br.com.nerdrapido.themoviedbapp.ui.abstracts.Presenter

/**
 * Created By FELIPE GUSBERTI @ 10/03/2020
 */
interface HomePresenter : Presenter<HomeView> {

    /**
     * This function is called when the user decides to logout.
     */
    fun logoutWasCalled()

    suspend fun loadDiscoverPage(page: Int): List<MovieListResultObject>

    suspend fun loadActionPage(page: Int): List<MovieListResultObject>

    suspend fun loadComedyPage(page: Int): List<MovieListResultObject>

    suspend fun loadScienceFictionPage(page: Int): List<MovieListResultObject>
}