package br.com.nerdrapido.themoviedbapp.ui.home

import br.com.nerdrapido.themoviedbapp.data.model.common.MovieListResultObject
import br.com.nerdrapido.themoviedbapp.ui.abstracts.navigation.NavigationPresenter

/**
 * Created By FELIPE GUSBERTI @ 10/03/2020
 */
interface HomePresenter : NavigationPresenter<HomeView> {

    suspend fun loadDiscoverPage(page: Int): List<MovieListResultObject>

    suspend fun loadActionPage(page: Int): List<MovieListResultObject>

    suspend fun loadComedyPage(page: Int): List<MovieListResultObject>

    suspend fun loadScienceFictionPage(page: Int): List<MovieListResultObject>
}