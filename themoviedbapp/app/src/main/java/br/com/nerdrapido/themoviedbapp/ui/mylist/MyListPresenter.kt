package br.com.nerdrapido.themoviedbapp.ui.mylist

import br.com.nerdrapido.themoviedbapp.data.model.common.MovieListResultObject
import br.com.nerdrapido.themoviedbapp.ui.abstracts.navigation.NavigationPresenter

/**
 * Created By FELIPE GUSBERTI @ 14/03/2020
 */
interface MyListPresenter : NavigationPresenter<MyListView> {

    suspend fun loadPage(page: Int): List<MovieListResultObject>

}