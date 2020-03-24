package br.com.nerdrapido.themoviedbapp.ui.mylist

import br.com.nerdrapido.themoviedbapp.data.model.common.MovieListResultObject
import br.com.nerdrapido.themoviedbapp.ui.abstracts.navigation.NavigationView


/**
 * Created By FELIPE GUSBERTI @ 14/03/2020
 */
interface MyListView : NavigationView {

    fun listSizeLoaded(totalPages: Int, pageSize: Int)

    fun listPageLoaded(list: List<MovieListResultObject>)

}