package br.com.nerdrapido.themoviedbapp.ui.home

import br.com.nerdrapido.themoviedbapp.ui.abstracts.navigation.NavigationPresenter

/**
 * Created By FELIPE GUSBERTI @ 10/03/2020
 */
interface HomePresenter : NavigationPresenter<HomeView> {

    fun loadDiscoverPage(page: Int)

    fun loadActionPage(page: Int)

    fun loadComedyPage(page: Int)

    fun loadScienceFictionPage(page: Int)
}